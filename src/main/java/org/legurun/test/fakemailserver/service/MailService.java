package org.legurun.test.fakemailserver.service;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.helper.SimpleMessageListener;

@Service
public class MailService implements SimpleMessageListener {
	private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

	@Override
	public boolean accept(String from, String recipient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deliver(String from, String recipient, InputStream data) throws TooMuchDataException, IOException {
		// TODO Auto-generated method stub
		
	}
}
