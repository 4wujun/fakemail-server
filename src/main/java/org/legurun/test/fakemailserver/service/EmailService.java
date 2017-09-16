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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.legurun.test.fakemailserver.dao.IEmailDao;
import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

/**
 * Email management service implementation.
 *
 * @author patlenain
 * @since 2017
 */
@Service
@Transactional
public class EmailService implements IEmailService {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			LoggerFactory.getLogger(EmailService.class);

	/**
	 * Sender service.
	 */
	@Autowired
	private ISenderService senderService;

	/**
	 * Email DAO.
	 */
	@Autowired
	private IEmailDao emailDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public PagedList<EmailSearchReport> search(
			final EmailSearchCommand searchCommand) {
		LOG.debug("Getting list of emails");
		Sender sender = null;
		if (searchCommand.getSenderId() != null) {
			sender = senderService.get(searchCommand.getSenderId());
		}
		return emailDao.search(sender, searchCommand.getRecipient(),
				searchCommand.getSentSince(), searchCommand.getSentBefore(),
				searchCommand.getSort(), searchCommand.getOrder(),
				searchCommand.getOffset(), searchCommand.getLimit());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public MimeMessage parse(final Email email) throws MessagingException {
		final Session session = Session.getDefaultInstance(new Properties());
		final ByteArrayInputStream inputStream =
				new ByteArrayInputStream(email.getMessage());
		return new MimeMessage(session, inputStream);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public boolean accept(final String from, final String recipient) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deliver(final String from, final String recipient,
			final InputStream data) throws IOException {
		LOG.debug("Receiving message from %s to %s", from, recipient);
		final Sender sender = senderService.getOrCreateSender(from);
		final Email email = new Email();
		email.setRecipient(recipient);
		email.setMessage(StreamUtils.copyToByteArray(data));
		email.setSentDate(new Date());
		try {
			final Session session =
					Session.getDefaultInstance(new Properties());
			final MimeMessage message =
					new MimeMessage(session,
							new ByteArrayInputStream(email.getMessage()));
			email.setSubject(message.getSubject());
			if (message.getSentDate() != null) {
				email.setSentDate(message.getSentDate());
			}
		}
		catch (final MessagingException ex) {
			LOG.error("Cannot analyze mail content", ex);
		}
		sender.addEmail(email);
		emailDao.persist(email);
	}
}
