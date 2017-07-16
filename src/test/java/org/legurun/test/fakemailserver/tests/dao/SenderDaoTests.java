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

import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, RepositoryConfig.class })
@TestPropertySource(value = "classpath:application-test-h2.properties")
public class SenderDaoTests {

	/**
	 * DAO to test.
	 */
	@Autowired
	private ISenderDao senderDao;

	@Test
	@Transactional
	@SuppressWarnings("checkstyle:multiplestringliterals")
	public void testFindByAddress() {
		createSenders();
		final Sender goodSender = senderDao.findByAddress("good@bar.com");
		Assert.assertNotNull(goodSender);
		Assert.assertEquals("good@bar.com", goodSender.getAddress());
	}

	@Test(expected = NoResultException.class)
	@Transactional
	@SuppressWarnings("checkstyle:multiplestringliterals")
	public void testFindByAddressUnknow() {
		createSenders();
		final Sender badSender = senderDao.findByAddress("unknown@foo.org");
		Assert.assertNull(badSender);
	}

	private void createSenders() {
		final Sender sender1 = new Sender();
		sender1.setAddress("test@foo.org");
		senderDao.persist(sender1);
		final Sender sender2 = new Sender();
		sender2.setAddress("test2@foo.org");
		senderDao.persist(sender2);
		final Sender sender3 = new Sender();
		sender3.setAddress("good@bar.com");
		senderDao.persist(sender3);
	}
}
