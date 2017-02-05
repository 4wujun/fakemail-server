package org.legurun.test.fakemailserver.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.legurun.test.fakemailserver.model.Sender;
import org.springframework.stereotype.Repository;

@Repository
public class SenderDao extends AbstractDao<Sender> implements ISenderDao {

	@Override
	public Sender findByAddress(String address) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("address", address));
		criteria.setCacheable(true);
		return (Sender) criteria.uniqueResult();
	}
}
