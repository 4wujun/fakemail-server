/*
 * Copyright (C) 2017 Patrice Le Gurun
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.legurun.test.fakemailserver.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Email_;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.model.Sender_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Email repository custom implementation.
 *
 * @author patlenain
 * @since 2017
 */
@Repository
public class EmailRepositoryImpl
		implements EmailRepositoryCustom {
	/**
	 * Entity manager.
	 */
	private final EntityManager entityManager;

	/**
	 * Create repository implementation.
	 * @param entityManager Entity manager
	 */
	public EmailRepositoryImpl(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * {@inheritDoc}
	 * Search emails.
	 *
	 * @param command Search params
	 * @return List of Emails or empty list
	 */
	@Override
	public Page<EmailSearchReport> search(
			final EmailSearchCommand command) {
		final CriteriaBuilder builder =
				this.entityManager.getCriteriaBuilder();
		final CriteriaQuery<EmailSearchReport> query =
				builder.createQuery(EmailSearchReport.class);
		final Root<Email> rootEmail = query.from(Email.class);
		final Join<Email, Sender> joinSender =
				rootEmail.join(Email_.sender, JoinType.INNER);
		query.select(
				builder.construct(
						EmailSearchReport.class,
						rootEmail.get(Email_.id),
						joinSender.get(Sender_.address),
						rootEmail.get(Email_.recipient),
						rootEmail.get(Email_.sentDate),
						rootEmail.get(Email_.subject)));
		final List<Predicate> predicates = new ArrayList<>();
		if (command.getSenderId() != null) {
			final Sender sender = this.entityManager.find(Sender.class,
				command.getSenderId());
			predicates.add(
				builder.equal(rootEmail.get(Email_.sender), sender));
		}
		if (StringUtils.hasText(command.getRecipient())) {
			// CHECKSTYLE:OFF
			predicates.add(
				builder.like(
					builder.upper(
							rootEmail.get(Email_.recipient)),
					"%" + command.getRecipient().trim().
						toUpperCase(Locale.ENGLISH) + "%"));
			// CHECKSTYLE:ON
		}
		if (command.getSentSince() != null) {
			predicates.add(
				builder.greaterThanOrEqualTo(
					rootEmail.get(Email_.sentDate), command.getSentSince()));
		}
		if (command.getSentBefore() != null) {
			predicates.add(
				builder.lessThanOrEqualTo(
					rootEmail.get(Email_.sentDate), command.getSentBefore()));
		}
		query.where(predicates.toArray(new Predicate[] {}));
//		if (pageable != null) {
//			final Sort sort = pageable.getSort();
//			if (sort != null) {
//				query.orderBy(QueryUtils.toOrders(sort, rootEmail, builder));
//			}
//		}
		final TypedQuery<EmailSearchReport> typedQuery =
				this.entityManager.createQuery(query);
		if (command.getFirstRow() != null) {
			typedQuery.setFirstResult(command.getFirstRow());
		}
		if (command.getMaxRows() != null) {
			typedQuery.setMaxResults(command.getMaxRows());
		}
		return new PageImpl<EmailSearchReport>(typedQuery.getResultList(),
				command.getPageable(), executeCountQuery(query));
	}

	/**
	 * Execute count query for search request.
	 * @param query Search query
	 * @return Count
	 */
	private long executeCountQuery(
			final CriteriaQuery<EmailSearchReport> query) {
		final CriteriaBuilder builder =
				this.entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		final Root<Email> rootEmail = countQuery.from(Email.class);
		rootEmail.join(Email_.sender, JoinType.INNER);
		countQuery.where(query.getRestriction());
		countQuery.select(builder.count(rootEmail));
		final TypedQuery<Long> typedQuery =
				this.entityManager.createQuery(countQuery);
		final List<Long> totals = typedQuery.getResultList();
		if (totals.size() == 1) {
			return totals.get(0);
		}
		return 0L;
	}
}
