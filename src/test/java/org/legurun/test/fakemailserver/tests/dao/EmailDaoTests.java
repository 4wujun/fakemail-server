package org.legurun.test.fakemailserver.tests.dao;

/*
 * Copyright (C) 2017 Patrice Le Gurun
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.config.RepositoryConfig;
import org.legurun.test.fakemailserver.config.RootConfig;
import org.legurun.test.fakemailserver.dao.IEmailDao;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, RepositoryConfig.class })
@TestPropertySource(value = "classpath:application-test-h2.properties")
@SuppressWarnings("checkstyle:multiplestringliterals")
public class EmailDaoTests {

	private Sender sender1;
	private Sender sender2;
	private Email email1;
	private Email email2;
	private Email email3;
	private Email email4;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * DAO to test.
	 */
	@Autowired
	private IEmailDao emailDao;

	@Test
	@Transactional
	public void testList() {
		final List<Email> list = emailDao.list();
		assertNotNull(list);
		assertEquals(4, list.size());
	}

	@Test
	@Transactional
	public void testGet() {
		final Email email = emailDao.get(email1.getId());
		assertNotNull(email);
		assertEquals(email1, email);
	}

	@Test
	@Transactional
	public void testDelete() {
		emailDao.delete(email1);
		final List<Email> list = emailDao.list();
		assertNotNull(list);
		assertEquals(3, list.size());
	}

	@Test
	@Transactional
	public void testSearchAll() {
		final PagedList<EmailSearchReport> list =
				emailDao.search(null, null, null, null, null, null);
		assertNotNull(list);
		assertEquals(4L, list.getTotal());
		assertEquals(4, list.getData().size());
	}

	@Test
	@Transactional
	public void testSearchBySender() {
		final PagedList<EmailSearchReport> list =
				emailDao.search(sender1, null, null, null, null, null);
		assertNotNull(list);
		assertEquals(3L, list.getTotal());
		assertEquals(3, list.getData().size());
	}

	@Test
	@Transactional
	public void testSearchByRecipient() {
		final PagedList<EmailSearchReport> list =
				emailDao.search(null, "test2", null, null, null, null);
		assertNotNull(list);
		assertEquals(1L, list.getTotal());
		assertEquals(1, list.getData().size());
		final EmailSearchReport result = list.getData().get(0);
		assertEquals(email2.getId(), result.getId());
	}

	@Test
	@Transactional
	public void testSearchBySentSince() {
		final Date sentSince = DateUtils.addDays(new Date(), -3);
		final PagedList<EmailSearchReport> list =
				emailDao.search(null, null, sentSince, null, null, null);
		assertNotNull(list);
		assertEquals(4L, list.getTotal());
		assertEquals(4, list.getData().size());
	}

	@Test
	@Transactional
	public void testSearchBySentBefore() {
		final Date sentBefore = DateUtils.addDays(new Date(), -1);
		final PagedList<EmailSearchReport> list =
				emailDao.search(null, null, null, sentBefore, null, null);
		assertNotNull(list);
		assertEquals(4L, list.getTotal());
		assertEquals(4, list.getData().size());
	}

	@Test
	@Transactional
	public void testSearchAllWithOffsetAndLimit() {
		final PagedList<EmailSearchReport> list =
				emailDao.search(null, null, null, null, 1, 2);
		assertNotNull(list);
		assertEquals(4L, list.getTotal());
		assertEquals(2, list.getData().size());
	}

	@Test
	@Transactional
	public void testSearchBySenderAndRecipient() {
		final PagedList<EmailSearchReport> list =
				emailDao.search(sender1, "test3", null, null, null, null);
		assertNotNull(list);
		assertEquals(1L, list.getTotal());
		assertEquals(1, list.getData().size());
	}


	@Before
	public void createSenders() {
		final Date sentDate = DateUtils.addDays(new Date(), -2);
		sender1 = new Sender();
		sender1.setAddress("test1@foo.org");
		entityManager.persist(sender1);
		sender2 = new Sender();
		sender2.setAddress("test2@foo.org");
		entityManager.persist(sender2);
		email1 = new Email();
		email1.setSender(sender1);
		email1.setRecipient("test1@bar.com");
		email1.setSentDate(sentDate);
		email1.setSubject("Test 1");
		email1.setMessage(new byte[] {});
		entityManager.persist(email1);
		email2 = new Email();
		email2.setSender(sender1);
		email2.setRecipient("test2@bar.com");
		email2.setSentDate(sentDate);
		email2.setSubject("Test 2");
		email2.setMessage(new byte[] {});
		entityManager.persist(email2);
		email3 = new Email();
		email3.setSender(sender1);
		email3.setRecipient("test3@bar.com");
		email3.setSentDate(sentDate);
		email3.setSubject("Test 3");
		email3.setMessage(new byte[] {});
		entityManager.persist(email3);
		email4 = new Email();
		email4.setSender(sender2);
		email4.setRecipient("test4@bar.com");
		email4.setSentDate(sentDate);
		email4.setSubject("Test 4");
		email4.setMessage(new byte[] {});
		entityManager.persist(email4);
		entityManager.flush();
	}
}
