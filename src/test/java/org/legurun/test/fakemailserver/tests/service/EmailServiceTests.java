package org.legurun.test.fakemailserver.tests.service;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.config.RepositoryConfig;
import org.legurun.test.fakemailserver.config.RootConfig;
import org.legurun.test.fakemailserver.dao.IEmailDao;
import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.legurun.test.fakemailserver.service.ISenderService;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

/**
 * Email service tests.
 * @author patrice
 * @since 2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, RepositoryConfig.class })
@TestPropertySource(value = "classpath:application-test-h2.properties")
public class EmailServiceTests {
	/**
	 * Email service to test.
	 */
	@Autowired
	@InjectMocks
	private IEmailService emailService;

	@Mock
	private IEmailDao emailDao;

	@Mock
	private ISenderService senderService;

	@Test
	public void testSearch() {
		final Sender sender = new Sender();
		sender.setId(1L);
		when(senderService.get(1L)).thenReturn(sender);
		final Date dateBefore = DateUtils.addDays(new Date(), 1);
		final Date dateSince = DateUtils.addDays(new Date(), -1);
		final EmailSearchReport email = new EmailSearchReport();
		final List<EmailSearchReport> listEmails =
				new ArrayList<EmailSearchReport>();
		listEmails.add(email);
		final PagedList<EmailSearchReport> pagedList =
				new PagedList<EmailSearchReport>();
		pagedList.setData(listEmails);
		pagedList.setTotal(1);
		when(
				emailDao.search(sender, "foo@bar.com",
						dateSince, dateBefore, 0, 25)).thenReturn(pagedList);
		ReflectionTestUtils.setField(emailService, "emailDao", emailDao);
		ReflectionTestUtils.setField(emailService, "senderService", senderService);

		final EmailSearchCommand command = new EmailSearchCommand();
		command.setSenderId(1L);
		command.setRecipient("foo@bar.com");
		command.setSentSince(dateSince);
		command.setSentBefore(dateBefore);
		command.setOffset(0);
		command.setLimit(25);

		final PagedList<EmailSearchReport> result =
				emailService.search(command);
		assertNotNull("search() must return a result", result);
		assertEquals(pagedList, result);
		verify(senderService, times(1)).get(eq(1L));
		verify(emailDao, times(1)).search(eq(sender),
			eq("foo@bar.com"), eq(dateSince),
			eq(dateBefore), eq(0), eq(25));
	}

	/**
	 * Test the accept method.
	 * @see IEmailService#accept(String, String)
	 */
	@Test
	public void testAccept() {
		assertTrue("accept() must be true",
				emailService.accept("sender@foo.com", "recipient@bar.org")
			);
	}

	@Before
	public void initMockito() {
		MockitoAnnotations.initMocks(this);
	}
}
