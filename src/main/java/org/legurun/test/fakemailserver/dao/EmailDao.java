package org.legurun.test.fakemailserver.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
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
	public PagedList<EmailSearchReport> search(Sender sender,
			String recipient, Date sentSince, Date sentBefore,
			Integer start, Integer limit,
			String sortProperty, SortOrder sortOrder) {
		PagedList<EmailSearchReport> pagedList = new PagedList<EmailSearchReport>();

		Criteria criteria = this.createCriteria();
		criteria.setReadOnly(true);
		criteria.createAlias("sender", "sender");
		if (sender != null) {
			criteria.add(Restrictions.eq("sender.id", sender.getId()));
		}
		if (StringUtils.hasText(recipient)) {
			criteria.add(Restrictions.ilike("recipient", recipient.trim(), MatchMode.ANYWHERE));
		}
		if (sentSince != null) {
			criteria.add(Restrictions.ge("sentDate", sentSince));
		}
		if (sentBefore != null) {
			criteria.add(Restrictions.le("sentDate", sentBefore));
		}
		criteria.setProjection(Projections.rowCount());
		pagedList.setTotal((Number)criteria.uniqueResult());

		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("id"), "id")
				.add(Projections.property("sender.address"), "sender")
				.add(Projections.property("recipient"), "recipient")
				.add(Projections.property("sentDate"), "sentDate")
				.add(Projections.property("subject"), "subject"));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(EmailSearchReport.class));
		if (start != null && limit != null) {
			criteria.setFirstResult(start);
			criteria.setMaxResults(limit);
		}
		if (sortProperty != null) {
			String propertyName = sortProperty;
			if ("sender".equals(sortProperty)) {
				propertyName = "sender.address";
			}
			criteria.addOrder(getOrder(propertyName, sortOrder));
		}
		criteria.addOrder(Order.asc("sentDate"));
		pagedList.setData(criteria.list());
		return pagedList;
	}
}
