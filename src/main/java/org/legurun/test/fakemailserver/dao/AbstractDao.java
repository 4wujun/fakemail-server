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

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.legurun.test.fakemailserver.model.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T extends AbstractEntity> implements IDao<T> {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public T get(final Long id) {
		return entityManager.find(persistentClass, id);
	}

	@Override
	public void persist(final T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void delete(final T entity) {
		entityManager.remove(entity);
	}

	@Override
	public List<T> list() {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(this.persistentClass);
		query.from(this.persistentClass);
		return this.getEntityManager().createQuery(query).getResultList();
	}
}
