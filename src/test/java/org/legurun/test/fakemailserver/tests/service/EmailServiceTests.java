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

package org.legurun.test.fakemailserver.tests.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.dao.IEmailDao;
import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.EmailService;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.legurun.test.fakemailserver.service.ISenderService;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

/**
 * Email service tests.
 * @author patlenain
 * @since 2017
 */
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test-h2.properties")
public class EmailServiceTests {
	/**
	 * Email service to test.
	 */
	@Autowired
	private IEmailService emailService;

	@MockBean
	private IEmailDao emailDao;

	@MockBean
	private ISenderService senderService;

	@Test
	public void testSearchWithSender() {
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
						dateSince, dateBefore, null, null, 0, 25))
			.thenReturn(pagedList);

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
			eq(dateBefore), isNull(), isNull(), eq(0), eq(25));
		verifyNoMoreInteractions(senderService);
		verifyNoMoreInteractions(emailDao);
	}

	@Test
	public void testSearchWithoutSender() {
		final Sender sender = new Sender();
		sender.setId(1L);
		when(senderService.get(1L)).thenReturn(sender);
		final PagedList<EmailSearchReport> pagedList =
				new PagedList<EmailSearchReport>();
		pagedList.setData(new ArrayList<EmailSearchReport>());
		pagedList.setTotal(0);
		when(
				emailDao.search(null, null,
						null, null, null, null, null, null)).
			thenReturn(pagedList);

		final EmailSearchCommand command = new EmailSearchCommand();
		command.setSenderId(null);

		final PagedList<EmailSearchReport> result =
				emailService.search(command);
		assertNotNull("search() must return a result", result);
		assertEquals(pagedList, result);
		verify(senderService, times(0)).get(1L);
		verify(emailDao, times(1)).search(isNull(),
				isNull(), isNull(), isNull(), isNull(),
				isNull(), isNull(), isNull());
		verifyNoMoreInteractions(senderService);
		verifyNoMoreInteractions(emailDao);
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

	@Test
	public void testParse() throws Exception {
		final Resource testMailFile =
				new ClassPathResource("testEmailServiceParse.txt");
		final Email email = new Email();
		email.setMessage(IOUtils.toByteArray(testMailFile.getInputStream()));

		final MimeMessage message =
				emailService.parse(email);
		final Address senderAddress =
				new InternetAddress("sender@foobar.org");
		final Address recipientAddress =
				new InternetAddress("recipient@bar.com");
		Assert.assertArrayEquals(
				new Address[] { senderAddress }, message.getFrom());
		Assert.assertArrayEquals(
				new Address[] { recipientAddress },
				message.getRecipients(RecipientType.TO));
		Assert.assertEquals("Test", message.getSubject());
		//Sat, 17 Jun 2017 19:06:06 +0200 (CEST)
	}


	@TestConfiguration
	static class TestContextConfiguration {
		@Bean
		public IEmailService emailService() {
			return new EmailService();
		}
	}
}
