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

import org.legurun.test.fakemailserver.model.Sender;

/**
 * Sender DAO.
 *
 * @author patrice
 * @since 2017
 */
public interface ISenderDao extends IDao<Sender> {

	/**
	 * Find a sender by his address.
	 * @param address Adress
	 * @return Sender or <code>null</code> if not found
	 */
	Sender findByAddress(String address);
}
