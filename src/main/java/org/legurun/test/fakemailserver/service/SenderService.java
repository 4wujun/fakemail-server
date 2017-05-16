package org.legurun.test.fakemailserver.service;

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

import java.util.List;

import org.legurun.test.fakemailserver.dao.ISenderDao;
import org.legurun.test.fakemailserver.model.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SenderService implements ISenderService {
	private static final Logger LOG = LoggerFactory.getLogger(SenderService.class);

	@Autowired
	private ISenderDao senderDao;

	@Override
	public List<Sender> list() {
		LOG.debug("Getting list of senders");
		return senderDao.list();
	}

	@Override
	public Sender get(final Long id) {
		LOG.debug("Getting sender id = {}", id);
		return senderDao.get(id);
	}
}
