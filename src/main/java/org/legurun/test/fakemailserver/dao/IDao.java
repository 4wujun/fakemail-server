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

import java.util.List;

import org.legurun.test.fakemailserver.model.AbstractEntity;

/**
 * Common DAO interface.
 *
 * @author patrice
 * @param <T> Entity class
 * @since 2017
 */
@SuppressWarnings("PMD.ShortClassName")
public interface IDao<T extends AbstractEntity> {

	/**
	 * Get an entity with his identifier.
	 * @param id Identifier
	 * @return Entity or <code>null</code> if not found
	 */
	@SuppressWarnings("PMD.ShortVariable")
	T get(Long id);

	/**
	 * Persist an entity.
	 * @param entity Entity to persist
	 */
	void persist(T entity);

	/**
	 * Delete an entity.
	 * @param entity Entity to delete
	 */
	void delete(T entity);

	/**
	 * List the entities.
	 * @return List of all entities
	 */
	List<T> list();
}
