package org.legurun.test.fakemailserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
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

		String selectQuery = " select new org.legurun.test.fakemailserver.dto.EmailSearchDTO(email.id, sender.address, email.recipient, email.dateSent, email.subject)";
		String selectCount = " select cast(count(email) as integer)";
		String fromQuery = " from Email email inner join email.sender sender";
		StringBuilder whereQuery = new StringBuilder(" where 1=1");

		Map<String, Object> parameters = new HashMap<String, Object>();
		for (FilterExtjs filter : filters) {
			if ("senderId".equals(filter.getProperty()) && filter.getValue() != null) {
				whereQuery.append(" and sender.id = :senderId");
				parameters.put("senderId", Long.parseLong(filter.getValue()));
			}
		}
		String orderByQuery = " order by email.dateSent";

		Session session = this.getSession();

		Query queryList = session.createQuery(selectQuery + fromQuery + whereQuery.toString() + orderByQuery);
		queryList.setProperties(parameters);
		queryList.setFirstResult(start);
		queryList.setMaxResults(start);
		queryList.setReadOnly(true);
		pagedList.setData(queryList.list());

		Query queryCount = session.createQuery(selectCount + fromQuery + whereQuery.toString());
		queryCount.setProperties(parameters);
		queryCount.setReadOnly(true);
		pagedList.setTotal((Integer)queryCount.uniqueResult());
		return pagedList;
	}
}
