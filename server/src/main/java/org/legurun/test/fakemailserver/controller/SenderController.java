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

package org.legurun.test.fakemailserver.controller;

import java.util.List;

import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.ISenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sender REST controller.
 *
 * @author patlenain
 * @since 2017
 */
@RestController
@RequestMapping("/api/sender")
public class SenderController {

	/**
	 * Sender service.
	 */
	@Autowired
	private ISenderService senderService;

	/**
	 * List the senders.
	 * @return Senders list
	 */
	@GetMapping
	public List<Sender> list() {
		return senderService.list();
	}

}
