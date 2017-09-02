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

package org.legurun.test.fakemailserver.dao;

import java.util.List;

import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

import org.legurun.test.fakemailserver.model.Sender;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Sender repository.
 *
 * @author patlenain
 * @since 2017
 */
public interface SenderRepository extends CrudRepository<Sender, Long> {

	/**
	 * Find a sender by his address.
	 *
	 * @param address
	 *            Adress
	 * @return Sender or <code>null</code> if not found
	 */
	@CacheResult(cacheName = SenderEnums.CACHE_NAME)
	Sender findByAddress(String address);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@CachePut(cacheName = SenderEnums.CACHE_NAME)
	<S extends Sender> S save(@CacheValue S entity);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@CacheRemove(cacheName = SenderEnums.CACHE_NAME)
	void delete(Sender entity);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@CacheRemoveAll(cacheName = SenderEnums.CACHE_NAME)
	void deleteAll();

	/**
	 * List all the senders.
	 * @return List of the senders
	 */
	@Query("select s from Sender s order by s.address")
	List<Sender> list();
}
