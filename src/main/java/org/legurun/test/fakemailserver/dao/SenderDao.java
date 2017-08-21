package org.legurun.test.fakemailserver.dao;

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

import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.legurun.test.fakemailserver.model.Sender;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

/**
 * Sender DAO implementation.
 *
 * @author patlenain
 * @since 2017
 */
@Repository
public class SenderDao extends AbstractDao<Sender> implements ISenderDao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@CacheResult(cacheName = "senders")
	public Sender findByAddress(final String address) {
		final CriteriaBuilder builder =
				this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Sender> query = builder.createQuery(Sender.class);
		final Root<Sender> root = query.from(Sender.class);
		query.where(
				builder.equal(root.get("address"), address)
		);
		return this.getEntityManager().createQuery(query).getSingleResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@CachePut(cacheName = "senders")
	public void persist(@CacheValue final Sender entity) {
		super.persist(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@CacheEvict(cacheNames = "senders")
	public void delete(final Sender entity) {
		super.delete(entity);
	}
}
