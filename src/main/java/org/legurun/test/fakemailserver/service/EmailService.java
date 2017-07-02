package org.legurun.test.fakemailserver.service;

/*******************************************************************************
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
 ******************************************************************************/

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.legurun.test.fakemailserver.dao.IEmailDao;
import org.legurun.test.fakemailserver.dao.ISenderDao;
import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.subethamail.smtp.TooMuchDataException;

@Service
@Transactional
public class EmailService implements IEmailService {
	private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private ISenderService senderService;

	@Autowired
	private IEmailDao emailDao;

	@Autowired
	private ISenderDao senderDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PagedList<EmailSearchReport> search(final EmailSearchCommand searchCommand) {
		LOG.debug("Getting list of emails");
		Sender sender = null;
		if (searchCommand.getSenderId() != null) {
			sender = senderService.get(searchCommand.getSenderId());
		}
		return emailDao.search(sender, searchCommand.getRecipient(),
				searchCommand.getSentSince(), searchCommand.getSentBefore(),
				searchCommand.getOffset(), searchCommand.getLimit());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MimeMessage parse(final Email email) throws MessagingException {
		Session session = Session.getDefaultInstance(new Properties());
		ByteArrayInputStream inputStream = new ByteArrayInputStream(email.getMessage());
		MimeMessage message = new MimeMessage(session, inputStream);
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(final String from, final String recipient) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deliver(final String from, final String recipient, final InputStream data) throws TooMuchDataException, IOException {
		LOG.debug(String.format("Receiving message from %s to %s", from, recipient));
		Sender sender = senderDao.findByAddress(from);
		if (sender == null) {
			sender = new Sender();
			sender.setAddress(from);
			senderDao.persist(sender);
		}
		Email email = new Email();
		email.setRecipient(recipient);
		email.setMessage(StreamUtils.copyToByteArray(data));
		email.setSentDate(new Date());
		try {
			Session session = Session.getDefaultInstance(new Properties());
			MimeMessage message = new MimeMessage(session, new ByteArrayInputStream(email.getMessage()));
			email.setSubject(message.getSubject());
			if (message.getSentDate() != null) {
				email.setSentDate(message.getSentDate());
			}
		}
		catch (MessagingException ex) {
			LOG.error("Cannot analyze mail content", ex);
		}
		sender.addEmail(email);
		emailDao.persist(email);
	}
}
