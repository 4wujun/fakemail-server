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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.subethamail.smtp.helper.SimpleMessageListener;

/**
 * Email service.
 *
 * @author patlenain
 * @since 2017
 */
public interface IEmailService extends SimpleMessageListener {

	/**
	 * Search emails.
	 * @param searchCommand Search parameters
	 * @return Search results
	 */
	PagedList<EmailSearchReport> search(EmailSearchCommand searchCommand);

	/**
	 * Parse email contents.
	 * @param email Email to parse
	 * @return Email contents
	 * @throws MessagingException Message parsing failed
	 */
	MimeMessage parse(Email email) throws MessagingException;
}
