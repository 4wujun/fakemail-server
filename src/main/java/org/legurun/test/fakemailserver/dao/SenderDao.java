package org.legurun.test.fakemailserver.dao;

/*******************************************************************************
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
 ******************************************************************************/

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
