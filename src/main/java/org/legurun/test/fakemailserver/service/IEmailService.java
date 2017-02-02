package org.legurun.test.fakemailserver.service;

import java.util.List;

import org.legurun.test.fakemailserver.model.Email;
import org.subethamail.smtp.helper.SimpleMessageListener;

public interface IEmailService extends SimpleMessageListener {

	List<Email> list();

}