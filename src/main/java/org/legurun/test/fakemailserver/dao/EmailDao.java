package org.legurun.test.fakemailserver.dao;

/*-
 * #%L
 * Fakemail server
 * %%
 * Copyright (C) 2017 Patrice Le Gurun
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class EmailDao extends AbstractDao<Email> implements IEmailDao {
	@Override
	public PagedList<EmailSearchReport> search(final Sender sender,
			final String recipient, final Date sentSince, final Date sentBefore,
			final Integer start, final Integer limit) {
		PagedList<EmailSearchReport> pagedList = new PagedList<EmailSearchReport>();

		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EmailSearchReport> query = builder.createQuery(EmailSearchReport.class);
		Root<Email> rootEmail = query.from(Email.class);
		Join<Email, Sender> joinSender = rootEmail.join("sender", JoinType.INNER);
		query.select(builder.construct(EmailSearchReport.class, rootEmail.get("id"), joinSender.get("address"), rootEmail.get("recipient"), rootEmail.get("sentDate"), rootEmail.get("subject")));
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (sender != null) {
			predicates.add(builder.equal(rootEmail.get("sender"), sender));
		}
		if (StringUtils.hasText(recipient)) {
			predicates.add(builder.like(builder.upper(rootEmail.get("recipient")), "%" + recipient.trim().toUpperCase() + "%"));
		}
		if (sentSince != null) {
			predicates.add(builder.greaterThanOrEqualTo(rootEmail.get("sentDate"), sentSince));
		}
		if (sentBefore != null) {
			predicates.add(builder.lessThanOrEqualTo(rootEmail.get("sentDate"), sentBefore));
		}
		query.where(predicates.toArray(new Predicate[] {}));
		List<Order> orders = new ArrayList<Order>();
/*		if (sortProperty != null) {
			String propertyName = sortProperty;
			if ("sender".equals(sortProperty)) {
				propertyName = "sender.address";
			}
			if (sortOrder == SortOrder.DESCENDING) {
				orders.add(builder.desc(rootEmail.get(propertyName)));
			}
			else {
				orders.add(builder.asc(rootEmail.get(propertyName)));
			}
		}*/
		orders.add(builder.asc(rootEmail.get("sentDate")));
		query.orderBy(orders);
		TypedQuery<EmailSearchReport> typedQueryList = this.getEntityManager().createQuery(query);
		if (start != null && limit != null) {
			typedQueryList.setFirstResult(start);
			typedQueryList.setMaxResults(limit);
		}
		pagedList.setData(typedQueryList.getResultList());

		CriteriaQuery<Number> queryCount = builder.createQuery(Number.class);
		Root<Email> rootCount = queryCount.from(Email.class);
		queryCount.select(builder.count(rootCount));
		queryCount.where(query.getRestriction());
		pagedList.setTotal(this.getEntityManager().createQuery(queryCount).getSingleResult());

		return pagedList;
	}
}
