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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MailDateFormat;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.dao.EmailRepository;
import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.EmailService;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.legurun.test.fakemailserver.service.ISenderService;
import org.mockito.internal.matchers.CapturingMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

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
	private EmailRepository emailRepository;

	@MockBean
	private ISenderService senderService;

	@Test
	public void testSearchWithSender() {
		final Sender sender = new Sender();
		sender.setId(1L);
		final Date dateBefore = DateUtils.addDays(new Date(), 1);
		final Date dateSince = DateUtils.addDays(new Date(), -1);
		final EmailSearchReport email = new EmailSearchReport();
		final List<EmailSearchReport> listEmails =
				new ArrayList<EmailSearchReport>();
		listEmails.add(email);
		final Page<EmailSearchReport> pagedList =
				new PageImpl<EmailSearchReport>(listEmails);
		final EmailSearchCommand command = new EmailSearchCommand();
		command.setSenderId(sender.getId());
		command.setRecipient("foo@bar.com");
		command.setSentSince(dateSince);
		command.setSentBefore(dateBefore);

		when(
			emailRepository.search(command))
			.thenReturn(pagedList);

		final Page<EmailSearchReport> result =
				emailService.search(command);
		assertNotNull("search() must return a result", result);
		assertEquals(pagedList, result);
		verify(emailRepository, times(1)).search(eq(command));
		verifyNoMoreInteractions(senderService);
		verifyNoMoreInteractions(emailRepository);
	}

	@Test
	public void testSearchWithoutSender() {
		final Sender sender = new Sender();
		sender.setId(1L);
		when(senderService.get(1L)).thenReturn(sender);
		final Page<EmailSearchReport> pagedList =
				new PageImpl<EmailSearchReport>(Collections.emptyList());

		final EmailSearchCommand command = new EmailSearchCommand();

		when(
			emailRepository.search(command)).
			thenReturn(pagedList);

		final Page<EmailSearchReport> result =
				emailService.search(command);
		assertNotNull("search() must return a result", result);
		assertEquals(pagedList, result);
		verify(senderService, times(0)).get(1L);
		verify(emailRepository, times(1)).search(eq(command));
		verifyNoMoreInteractions(senderService);
		verifyNoMoreInteractions(emailRepository);
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
		email.setMessage(
				StreamUtils.copyToByteArray(testMailFile.getInputStream()));

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
		final DateFormat dateFormat = new MailDateFormat();
		final Date expectedDate =
				dateFormat.parse("Sat, 17 Jun 2017 19:06:06 +0200 (CEST)");
		Assert.assertEquals(expectedDate, message.getSentDate());
	}

	@Test
	public void testDeliver() throws Exception {
		final Sender sender = new Sender();
		sender.setId(1L);		
		when(senderService.getOrCreateSender("sender@foobar.org"))
			.thenReturn(sender);
		CapturingMatcher<Email> emailCapturing = new CapturingMatcher<Email>();
		when(emailRepository.save(any(Email.class)))
			.thenAnswer((i) -> {
				emailCapturing.captureFrom(i.getArgument(0));
				return i.getArgument(0);
			});

		final Resource testMailFile =
				new ClassPathResource("testEmailServiceDeliver.txt");
		emailService.deliver("sender@foobar.org", "recipient@bar.com",
				testMailFile.getInputStream());

		final Email email = emailCapturing.getLastValue();

		Assert.assertNotNull(email);
		Assert.assertEquals(sender, email.getSender());
		Assert.assertEquals("recipient@bar.com", email.getRecipient());
		final DateFormat dateFormat = new MailDateFormat();
		final Date expectedDate =
				dateFormat.parse("Sat, 17 Jun 2017 19:06:06 +0200 (CEST)");
		Assert.assertEquals(expectedDate, email.getSentDate());
		
		verify(senderService, times(1))
			.getOrCreateSender(eq("sender@foobar.org"));
		verify(emailRepository, times(1))
			.save(any(Email.class));
		verifyNoMoreInteractions(senderService);
		verifyNoMoreInteractions(emailRepository);
	}


	@TestConfiguration
	static class TestContextConfiguration {
		@Bean
		public IEmailService emailService() {
			return new EmailService();
		}
	}
}
