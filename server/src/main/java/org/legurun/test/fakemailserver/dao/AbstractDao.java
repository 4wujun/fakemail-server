package org.legurun.test.fakemailserver.dao;

/*
 * Copyright (C) 2017-2018 Patrice Le Gurun
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

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.legurun.test.fakemailserver.model.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Common class for DAOs.
 *
 * @author patlenain
 * @param <T> Persisted class
 * @since 2017
 */
public abstract class AbstractDao<T extends AbstractEntity>
		implements IDao<T> {

	/**
	 * Entity manager.
	 */
	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Class persisted by this DAO.
	 */
	private final Class<T> persistentClass;

	/**
	 * Construct this DAO by getting the parametrized type.
	 */
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>)
				((ParameterizedType) this.getClass().getGenericSuperclass()).
				getActualTypeArguments()[0];
	}

	/**
	 * Get the entity manager.
	 * @return Entity manager
	 */
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("PMD.ShortVariable")
	public T get(final Long id) {
		return entityManager.find(persistentClass, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(final T entity) {
		entityManager.persist(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final T entity) {
		entityManager.remove(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> list() {
		final CriteriaBuilder builder =
				this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<T> query =
				builder.createQuery(this.persistentClass);
		query.from(this.persistentClass);
		return this.getEntityManager().createQuery(query).getResultList();
	}
}
