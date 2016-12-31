package org.legurun.test.fakemailserver.mail;

import java.io.IOException;
import java.io.InputStream;

import org.legurun.test.fakemailserver.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.helper.SimpleMessageListener;

@Component
public class MailListener implements SimpleMessageListener {
	private static final Logger LOG = LoggerFactory.getLogger(MailListener.class);

	@Autowired
	private MailService mailService;

	@Override
	public boolean accept(String from, String recipient) {
		return true;
	}

	@Override
	public void deliver(String from, String recipient, InputStream data) throws TooMuchDataException, IOException {
		LOG.debug("deliver - from " + from + " to " + recipient);
	}

}
