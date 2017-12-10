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

package org.legurun.test.fakemailserver.service;

import java.util.List;

import org.legurun.test.fakemailserver.dao.SenderRepository;
import org.legurun.test.fakemailserver.model.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	 * Sender repository.
	 */
	@Autowired
	private SenderRepository senderRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Sender> list() {
		LOG.debug("Getting list of senders");
		return senderRepository.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	@SuppressWarnings("PMD.ShortVariable")
	public Sender get(final Long id) {
		LOG.debug("Getting sender id = {}", id);
		return senderRepository.findOne(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Sender getOrCreateSender(final String address) {
		LOG.debug("Getting sender by address '{}'", address);
		Sender sender = senderRepository.findByAddress(address);
		if (sender == null) {
			LOG.info("No sender, creating a new one");
			sender = new Sender();
			sender.setAddress(address);
			senderRepository.save(sender);
		}
		return sender;
	}
}
