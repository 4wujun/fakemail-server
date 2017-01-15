package org.legurun.test.fakemailserver.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.legurun.test.fakemailserver.model.Email;
import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.helper.SimpleMessageListener;

public interface IEmailService extends SimpleMessageListener {

	List<Email> list();

	@Override
	boolean accept(String from, String recipient);

	@Override
	void deliver(String from, String recipient, InputStream data) throws TooMuchDataException, IOException;

}