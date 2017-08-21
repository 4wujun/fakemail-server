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

import java.util.Date;

import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.utils.PagedList;

/**
 * Email DAO.
 *
 * @author patlenain
 * @since 2017
 */
public interface IEmailDao extends IDao<Email> {
	/**
	 * Search emails.
	 * @param sender Sender
	 * @param recipient Recipient address
	 * @param sentSince Date since the email was sent
	 * @param sentBefore Date before the email was sent
	 * @param start Start index for pagination
	 * @param limit Limit results for pagination
	 * @return Search result
	 */
	PagedList<EmailSearchReport> search(Sender sender,
		String recipient, Date sentSince, Date sentBefore,
		Integer start, Integer limit);
}
