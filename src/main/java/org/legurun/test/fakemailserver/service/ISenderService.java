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

import org.legurun.test.fakemailserver.model.Sender;

/**
 * Sender service.
 *
 * @author patrice
 * @since 2017
 */
public interface ISenderService {

	/**
	 * Get a sender by identifier.
	 * @param id Identifier
	 * @return Sender or <code>null</code> if not found
	 */
	Sender get(Long id);

	/**
	 * List all the senders.
	 * @return Senders list.
	 */
	List<Sender> list();
}
