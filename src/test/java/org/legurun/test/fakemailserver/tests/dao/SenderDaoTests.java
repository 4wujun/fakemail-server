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

import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.config.RepositoryConfig;
import org.legurun.test.fakemailserver.config.RootConfig;
import org.legurun.test.fakemailserver.dao.ISenderDao;
import org.legurun.test.fakemailserver.model.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, RepositoryConfig.class })
@TestPropertySource(value = "classpath:application-test-h2.properties")
public class SenderDaoTests {

	private Sender sender1;
	private Sender sender2;
	private Sender sender3;

	/**
	 * DAO to test.
	 */
	@Autowired
	private ISenderDao senderDao;

	@Test
	@Transactional
	@SuppressWarnings("checkstyle:multiplestringliterals")
	public void testFindByAddress() {
		final Sender goodSender = senderDao.findByAddress("good@bar.com");
		assertNotNull(goodSender);
		assertEquals("good@bar.com", goodSender.getAddress());
	}

	@Test(expected = NoResultException.class)
	@Transactional
	@SuppressWarnings("checkstyle:multiplestringliterals")
	public void testFindByAddressUnknow() {
		final Sender badSender = senderDao.findByAddress("unknown@foo.org");
		assertNull(badSender);
	}

	@Test
	@Transactional
	@SuppressWarnings("checkstyle:multiplestringliterals")
	public void testList() {
		final List<Sender> list = senderDao.list();
		assertNotNull(list);
		assertEquals(3, list.size());
	}

	@Test
	@Transactional
	@SuppressWarnings("checkstyle:multiplestringliterals")
	public void testGet() {
		final Sender sender = senderDao.get(sender1.getId());
		assertNotNull(sender);
		assertEquals(sender1, sender);
	}

	@Test
	@Transactional
	@SuppressWarnings("checkstyle:multiplestringliterals")
	public void testDelete() {
		senderDao.delete(sender1);
		final List<Sender> list = senderDao.list();
		assertNotNull(list);
		assertEquals(2, list.size());
	}

	@Before
	public void createSenders() {
		sender1 = new Sender();
		sender1.setAddress("test@foo.org");
		senderDao.persist(sender1);
		sender2 = new Sender();
		sender2.setAddress("test2@foo.org");
		senderDao.persist(sender2);
		sender3 = new Sender();
		sender3.setAddress("good@bar.com");
		senderDao.persist(sender3);
	}
}
