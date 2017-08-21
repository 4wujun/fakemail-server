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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.legurun.test.fakemailserver.utils.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * DAO implementation for Email entity.
 *
 * @author patlenain
 * @since 2017
 * @see Email
 */
@Repository
@SuppressWarnings({ "PMD.CyclomaticComplexity",
	"PMD.ModifiedCyclomaticComplexity", "PMD.StdCyclomaticComplexity" })
public class EmailDao extends AbstractDao<Email> implements IEmailDao {
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "checkstyle:MultipleStringLiterals",
		"checkstyle:NPathComplexity",
		"PMD.AvoidDuplicateLiterals", "PMD.NPathComplexity" })
	@Override
	public PagedList<EmailSearchReport> search(final Sender sender,
			final String recipient, final Date sentSince,
			final Date sentBefore, final String sort, final String order,
			final Integer start, final Integer limit) {
		final PagedList<EmailSearchReport> pagedList =
				new PagedList<EmailSearchReport>();

		final CriteriaBuilder builder =
				this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<EmailSearchReport> query =
				builder.createQuery(EmailSearchReport.class);
		final Root<Email> rootEmail = query.from(Email.class);
		final Join<Email, Sender> joinSender =
				rootEmail.join("sender", JoinType.INNER);
		query.select(
				builder.construct(
						EmailSearchReport.class, rootEmail.get("id"),
						joinSender.get("address"), rootEmail.get("recipient"),
						rootEmail.get("sentDate"), rootEmail.get("subject")));
		final List<Predicate> predicates = new ArrayList<Predicate>();
		if (sender != null) {
			predicates.add(builder.equal(rootEmail.get("sender"), sender));
		}
		if (StringUtils.hasText(recipient)) {
			predicates.add(
					builder.like(
							builder.upper(
									rootEmail.get("recipient")),
							"%" + recipient.trim().
								toUpperCase(Locale.ENGLISH) + "%"));
		}
		if (sentSince != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(
							rootEmail.get("sentDate"), sentSince));
		}
		if (sentBefore != null) {
			predicates.add(
					builder.lessThanOrEqualTo(
							rootEmail.get("sentDate"), sentBefore));
		}
		query.where(predicates.toArray(new Predicate[] {}));
		final List<Order> orders = new ArrayList<Order>();
		if (sort != null) {
			String propertyName = sort;
			if ("sender".equals(sort)) {
				propertyName = "sender.address";
			}
			if (SortOrder.isDescending(order)) {
				orders.add(builder.desc(rootEmail.get(propertyName)));
			}
			else {
				orders.add(builder.asc(rootEmail.get(propertyName)));
			}
		}
		orders.add(builder.asc(rootEmail.get("sentDate")));
		query.orderBy(orders);
		final TypedQuery<EmailSearchReport> typedQueryList =
				this.getEntityManager().createQuery(query);
		if (start != null && limit != null) {
			typedQueryList.setFirstResult(start);
			typedQueryList.setMaxResults(limit);
		}
		pagedList.setData(typedQueryList.getResultList());

		final CriteriaQuery<Number> queryCount =
				builder.createQuery(Number.class);
		final Root<Email> rootCount = queryCount.from(Email.class);
		queryCount.select(builder.count(rootCount));
		queryCount.where(query.getRestriction());
		pagedList.setTotal(this.getEntityManager().
				createQuery(queryCount).getSingleResult());

		return pagedList;
	}
}
