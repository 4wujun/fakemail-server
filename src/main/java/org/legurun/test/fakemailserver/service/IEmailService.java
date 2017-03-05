package org.legurun.test.fakemailserver.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.legurun.test.fakemailserver.dto.EmailSearchDTO;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.subethamail.smtp.helper.SimpleMessageListener;

public interface IEmailService extends SimpleMessageListener {

	PagedList<EmailSearchDTO> search(Sender sender,
			String recipient, Integer start, Integer limit);

	MimeMessage parse(Email email) throws MessagingException;
}