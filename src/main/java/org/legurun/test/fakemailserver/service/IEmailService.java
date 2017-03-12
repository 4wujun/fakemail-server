package org.legurun.test.fakemailserver.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.legurun.test.fakemailserver.utils.SortOrder;
import org.subethamail.smtp.helper.SimpleMessageListener;

public interface IEmailService extends SimpleMessageListener {

	PagedList<EmailSearchReport> search(EmailSearchCommand searchCommand,
			Integer offset, Integer limit, String sortProperty, SortOrder sortOrder);

	MimeMessage parse(Email email) throws MessagingException;
}