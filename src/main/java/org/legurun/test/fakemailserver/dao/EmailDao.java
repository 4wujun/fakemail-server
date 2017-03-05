package org.legurun.test.fakemailserver.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.legurun.test.fakemailserver.dto.EmailSearchDTO;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.utils.FilterExtjs;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDao extends AbstractDao<Email> implements IEmailDao {
	@SuppressWarnings("unchecked")
	@Override
	public PagedList<EmailSearchDTO> search(List<FilterExtjs> filters, Integer start, Integer limit) {
		PagedList<EmailSearchDTO> pagedList = new PagedList<EmailSearchDTO>();

		Criteria criteria = this.createCriteria();
		criteria.setReadOnly(true);
		criteria.createAlias("sender", "sender");
		for (FilterExtjs filter : filters) {
			if ("senderId".equals(filter.getProperty()) && filter.getValue() != null) {
				criteria.add(Restrictions.eq("sender.id", Long.parseLong(filter.getValue())));
			}
		}
		criteria.setProjection(Projections.rowCount());
		pagedList.setTotal((Number)criteria.uniqueResult());

		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("id"), "id")
				.add(Projections.property("sender.address"), "sender")
				.add(Projections.property("recipient"), "recipient")
				.add(Projections.property("dateSent"), "dateSent")
				.add(Projections.property("subject"), "subject"));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(EmailSearchDTO.class));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.asc("dateSent"));
		pagedList.setData(criteria.list());
		return pagedList;
	}
}
