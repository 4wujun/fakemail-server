package org.legurun.test.fakemailserver.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.legurun.test.fakemailserver.dto.EmailSearchDTO;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.utils.FilterExtjs;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.subethamail.smtp.helper.SimpleMessageListener;

public interface IEmailService extends SimpleMessageListener {

	PagedList<EmailSearchDTO> search(List<FilterExtjs> filters, Integer start, Integer limit);

	MimeMessage parse(Email email) throws MessagingException;
}