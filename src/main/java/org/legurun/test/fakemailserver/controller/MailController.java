package org.legurun.test.fakemailserver.controller;

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

import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Email REST controller.
 *
 * @author patrice
 * @since 2017
 */
@RestController
@RequestMapping("/api/mail")
public class MailController {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			LoggerFactory.getLogger(MailController.class);

	/**
	 * Email service.
	 */
	@Autowired
	private IEmailService emailService;

	/**
	 * Search the emails.
	 * @param searchCommand Search criteria
	 * @return Search result
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagedList<EmailSearchReport> search(
			@RequestBody(required = false)
			final EmailSearchCommand searchCommand) {
		LOG.debug("Params : {}", searchCommand);
		return emailService.search(searchCommand);
	}
}
