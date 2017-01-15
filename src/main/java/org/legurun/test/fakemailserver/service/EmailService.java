package org.legurun.test.fakemailserver.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.legurun.test.fakemailserver.dao.IEmailDao;
import org.legurun.test.fakemailserver.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.subethamail.smtp.TooMuchDataException;

@Service
@Transactional
public class EmailService implements IEmailService {
	private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private IEmailDao emailDao;

	/* (non-Javadoc)
	 * @see org.legurun.test.fakemailserver.service.IEmailService#list()
	 */
	@Override
	public List<Email> list() {
		LOG.debug("Getting list of emails");
		return emailDao.list();
	}

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
