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

package org.legurun.test.fakemailserver.tests.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.config.RepositoryConfig;
import org.legurun.test.fakemailserver.config.RootConfig;
import org.legurun.test.fakemailserver.config.WebMvcConfig;
import org.legurun.test.fakemailserver.controller.MailController;
import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.legurun.test.fakemailserver.tests.utils.TestUtils;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class,
		RepositoryConfig.class, WebMvcConfig.class })
@TestPropertySource(value = "classpath:application-test-h2.properties")
@WebAppConfiguration
public class MailControllerTests {

	private MockMvc mockMvc;

	@Mock
	private IEmailService emailService;

	@Autowired
	@InjectMocks
	private MailController mailController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc =
			MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testSearchByGet() throws Exception {
		mockMvc.perform(get("/api/mail"))
			.andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void testSearchByPost() throws Exception {
		final EmailSearchCommand command =
				new EmailSearchCommand();
		command.setSenderId(1L);
		command.setRecipient("test@foo.org");
		command.setSentSince(new Date());
		command.setSentBefore(new Date());
		command.setOffset(10);
		command.setLimit(20);
		final EmailSearchReport report =
				new EmailSearchReport();
		report.setId(1L);
		report.setSender("foor@bar.com");
		report.setRecipient("test@foo.org");
		report.setSentDate(new Date());
		report.setSubject("Test mail controller");
		final PagedList<EmailSearchReport> list =
				new PagedList<EmailSearchReport>();
		list.setData(Arrays.asList(report));
		list.setTotal(5);

		when(emailService.search(command)).thenReturn(list);

		final MockHttpServletRequestBuilder postBuilder =
				post("/api/mail");
		postBuilder.accept(MediaType.APPLICATION_JSON_UTF8)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(TestUtils.convertObjectToJson(command));
		mockMvc.perform(postBuilder)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("total", is(5)))
			.andExpect(jsonPath("data", hasSize(1)))
			.andExpect(jsonPath("data[0].id", is(report.getId().intValue())))
			.andExpect(jsonPath("data[0].recipient",
					is(report.getRecipient())))
			.andExpect(jsonPath("data[0].sentDate",
					is(report.getSentDate().getTime())))
			.andExpect(jsonPath("data[0].subject", is(report.getSubject())))
			;
		verify(emailService, times(1)).search(command);
		verifyNoMoreInteractions(emailService);
	}

	@Before
	public void initMockito() {
		MockitoAnnotations.initMocks(this);
	}
}
