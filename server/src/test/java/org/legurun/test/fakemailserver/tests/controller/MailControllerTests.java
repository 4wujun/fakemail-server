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
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.controller.MailController;
import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@AutoConfigureDataJpa
@WebMvcTest(MailController.class)
public class MailControllerTests {

	private MockMvc mockMvc;

	@Autowired
	private MailController mailController;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IEmailService emailService;

	@Before
	public void setUp() {
		final PageableArgumentResolver pageableResolver =
				new PageableHandlerMethodArgumentResolver();
		this.mockMvc = MockMvcBuilders.standaloneSetup(mailController).
				setCustomArgumentResolvers(pageableResolver).
				setMessageConverters(new MappingJackson2HttpMessageConverter()).
				build();
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
		final EmailSearchReport report =
				new EmailSearchReport();
		report.setId(1L);
		report.setSender("foor@bar.com");
		report.setRecipient("test@foo.org");
		report.setSentDate(new Date());
		report.setSubject("Test mail controller");
		final Page<EmailSearchReport> list =
				new PageImpl<EmailSearchReport>(
						Arrays.asList(report), null, 5);

		when(
			emailService.search(eq(command))).
			thenReturn(list);

		final MockHttpServletRequestBuilder postBuilder =
				post("/api/mail").param("page", "0").param("size", "50")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(command));
		mockMvc.perform(postBuilder)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("totalElements", is(5)))
			.andExpect(jsonPath("content", hasSize(1)))
			.andExpect(jsonPath("content[0].id", is(report.getId().intValue())))
			.andExpect(jsonPath("content[0].recipient",
					is(report.getRecipient())))
			.andExpect(jsonPath("content[0].sentDate",
					is(report.getSentDate().getTime())))
			.andExpect(jsonPath("content[0].subject", is(report.getSubject())))
			;
		verify(emailService, times(1)).search(eq(command));
		verifyNoMoreInteractions(emailService);
	}
}
