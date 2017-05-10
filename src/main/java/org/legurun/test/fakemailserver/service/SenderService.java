package org.legurun.test.fakemailserver.service;

import java.util.List;

import org.legurun.test.fakemailserver.dao.ISenderDao;
import org.legurun.test.fakemailserver.model.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SenderService implements ISenderService {
	private static final Logger LOG = LoggerFactory.getLogger(SenderService.class);

	@Autowired
	private ISenderDao senderDao;

	@Override
	public List<Sender> list() {
		LOG.debug("Getting list of senders");
		return senderDao.list();
	}

	@Override
	public Sender get(final Long id) {
		LOG.debug("Getting sender id = {}", id);
		return senderDao.get(id);
	}
}
