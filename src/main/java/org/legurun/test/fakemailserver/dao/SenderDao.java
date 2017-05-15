package org.legurun.test.fakemailserver.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.legurun.test.fakemailserver.model.Sender;
import org.springframework.stereotype.Repository;

@Repository
public class SenderDao extends AbstractDao<Sender> implements ISenderDao {

	@Override
	public Sender findByAddress(final String address) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Sender> query = builder.createQuery(Sender.class);
		Root<Sender> root = query.from(Sender.class);
		query.where(
				builder.equal(root.get("address"), address)
		);
		return this.getEntityManager().createQuery(query).getSingleResult();
	}
}
