package org.legurun.test.fakemailserver.dao;

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
import org.legurun.test.fakemailserver.utils.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class EmailDao extends AbstractDao<Email> implements IEmailDao {
	@SuppressWarnings("unchecked")
	@Override
	public PagedList<EmailSearchReport> search(final Sender sender,
			final String recipient, final Date sentSince, final Date sentBefore,
			final Integer start, final Integer limit,
			final String sortProperty, final SortOrder sortOrder) {
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
		query.where(predicates.toArray(new Predicate[] { }));
		List<Order> orders = new ArrayList<Order>();
		if (sortProperty != null) {
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
		}
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
