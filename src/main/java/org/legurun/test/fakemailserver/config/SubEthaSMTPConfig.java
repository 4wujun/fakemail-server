package org.legurun.test.fakemailserver.config;

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

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.legurun.test.fakemailserver.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

/**
 * Configuration for the SMTP server.
 *
 * @author patrice
 * @since 2017
 * @see SMTPServer
 */
@Component
@ComponentScan(basePackages = "org.legurun.test.fakemailserver.mail")
public class SubEthaSMTPConfig {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			LoggerFactory.getLogger(SubEthaSMTPConfig.class);

	/**
	 * Execution environment.
	 */
	@Autowired
	private Environment environment;

	/**
	 * Get the SMTP server.
	 *
	 * @param mailService Mail managment service.
	 * @return SMTP server
	 * @throws UnknownHostException Listening host unknown
	 */
	@Autowired
	@Bean(initMethod = "start", destroyMethod = "stop")
	public SMTPServer smtpServer(final IEmailService mailService)
			throws UnknownHostException {
		LOG.trace("Initialize smtpServer");
		final MessageHandlerFactory factory =
				new SimpleMessageListenerAdapter(mailService);

		final String listenHost =
				environment.getRequiredProperty("smtp.listen.host");
		final int listenPort =
				environment.getRequiredProperty("smtp.listen.port",
						Integer.class);
		LOG.debug("Listening on {}:{}", listenHost, listenPort);
		final SMTPServer server = new SMTPServer(factory);
		server.setSoftwareName("Fakemail Server");
		server.setBindAddress(InetAddress.getByName(listenHost));
		server.setPort(listenPort);
		return server;
	}
}
