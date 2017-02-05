package org.legurun.test.fakemailserver.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.legurun.test.fakemailserver.dao.IEmailDao;
import org.legurun.test.fakemailserver.dao.ISenderDao;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
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
	private ISenderDao senderDao;
	@Autowired
	private IEmailDao emailDao;

	@Override
	public List<Email> list() {
		LOG.debug("Getting list of emails");
		return emailDao.list();
	}

	@Override
	public MimeMessage parse(Email email) throws MessagingException {
		Session session = Session.getDefaultInstance(new Properties());
		ByteArrayInputStream inputStream = new ByteArrayInputStream(email.getMessage());
		MimeMessage message = new MimeMessage(session, inputStream);
		return message;
	}

	@Override
	public boolean accept(String from, String recipient) {
		return true;
	}

	@Override
	public void deliver(String from, String recipient, InputStream data) throws TooMuchDataException, IOException {
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
		email.setDateSent(new Date());
		try {
			Session session = Session.getDefaultInstance(new Properties());
			MimeMessage message = new MimeMessage(session, new ByteArrayInputStream(email.getMessage()));
			email.setSubject(message.getSubject());
			if (message.getSentDate() != null) {
				email.setDateSent(message.getSentDate());
			}
		}
		catch (MessagingException ex) {
			LOG.error("Cannot analyze mail content", ex);
		}
		sender.addEmail(email);
		emailDao.persist(email);
	}
}
