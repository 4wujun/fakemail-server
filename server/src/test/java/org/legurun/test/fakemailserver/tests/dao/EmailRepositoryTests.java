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

package org.legurun.test.fakemailserver.tests.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.dao.EmailRepository;
import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaAuditing
@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class EmailRepositoryTests {

	private Sender sender1;
	private Sender sender2;
	private Email email1;
	private Email email2;
	private Email email3;
	private Email email4;

	@Autowired
	private TestEntityManager entityManager;

	/**
	 * DAO to test.
	 */
	@Autowired
	private EmailRepository emailRepository;

	@Test
	@Transactional
	public void testSearchAll() {
		final EmailSearchCommand command = new EmailSearchCommand();
		final Page<EmailSearchReport> list =
				emailRepository.search(command);
		assertNotNull(list);
		assertEquals(4L, list.getTotalElements());
		assertEquals(4, list.getNumberOfElements());
	}

	@Test
	@Transactional
	public void testSearchBySender() {
		final EmailSearchCommand command = new EmailSearchCommand();
		command.setSenderId(sender1.getId());
		final Page<EmailSearchReport> list =
				emailRepository.search(command);
		assertNotNull(list);
		assertEquals(3L, list.getTotalElements());
		assertEquals(3, list.getNumberOfElements());
	}

	@Test
	@Transactional
	public void testSearchByRecipient() {
		final EmailSearchCommand command = new EmailSearchCommand();
		command.setRecipient("test2");
		final Page<EmailSearchReport> list =
				emailRepository.search(command);
		assertNotNull(list);
		assertEquals(1L, list.getTotalElements());
		assertEquals(1, list.getNumberOfElements());
		final EmailSearchReport result = list.getContent().get(0);
		assertEquals(email2.getId(), result.getId());
	}

	@Test
	@Transactional
	public void testSearchBySentSince() {
		final EmailSearchCommand command = new EmailSearchCommand();
		final Date sentSince = DateUtils.addDays(new Date(), -3);
		command.setSentSince(sentSince);
		final Page<EmailSearchReport> list =
				emailRepository.search(command);
		assertNotNull(list);
		assertEquals(4L, list.getTotalElements());
		assertEquals(4, list.getNumberOfElements());
	}

	@Test
	@Transactional
	public void testSearchBySentBefore() {
		final EmailSearchCommand command = new EmailSearchCommand();
		final Date sentBefore = DateUtils.addDays(new Date(), -1);
		command.setSentBefore(sentBefore);
		final Page<EmailSearchReport> list =
				emailRepository.search(command);
		assertNotNull(list);
		assertEquals(4L, list.getTotalElements());
		assertEquals(4, list.getNumberOfElements());
	}

	@Test
	@Transactional
	public void testSearchAllWithOffsetAndLimit() {
		final EmailSearchCommand command = new EmailSearchCommand();
		command.setFirstRow(1);
		command.setMaxRows(2);
		final Page<EmailSearchReport> list =
				emailRepository.search(command);
		assertNotNull(list);
		assertEquals(4L, list.getTotalElements());
		assertEquals(2, list.getSize());
	}

	@Test
	@Transactional
	public void testSearchBySenderAndRecipient() {
		final EmailSearchCommand command = new EmailSearchCommand();
		command.setSenderId(sender1.getId());
		command.setRecipient("test3");
		final Page<EmailSearchReport> list =
				emailRepository.search(command);
		assertNotNull(list);
		assertEquals(1L, list.getTotalElements());
		assertEquals(1, list.getNumberOfElements());
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
