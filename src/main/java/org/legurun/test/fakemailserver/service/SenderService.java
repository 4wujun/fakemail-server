package org.legurun.test.fakemailserver.service;

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

import javax.cache.annotation.CacheResult;

import org.legurun.test.fakemailserver.dao.ISenderDao;
import org.legurun.test.fakemailserver.model.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sender service implementation.
 *
 * @author patlenain
 * @since 2017
 */
@Service
@Transactional
public class SenderService implements ISenderService {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			LoggerFactory.getLogger(SenderService.class);

	/**
	 * Sender DAO.
	 */
	@Autowired
	private ISenderDao senderDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@CacheResult(cacheName = "senders")
	public List<Sender> list() {
		LOG.debug("Getting list of senders");
		return senderDao.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@CacheResult(cacheName = "senders")
	@SuppressWarnings("PMD.ShortVariable")
	public Sender get(final Long id) {
		LOG.debug("Getting sender id = {}", id);
		return senderDao.get(id);
	}
}
