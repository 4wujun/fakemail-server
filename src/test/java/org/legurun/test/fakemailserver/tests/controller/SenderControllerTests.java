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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.config.RepositoryConfig;
import org.legurun.test.fakemailserver.config.RootConfig;
import org.legurun.test.fakemailserver.config.WebMvcConfig;
import org.legurun.test.fakemailserver.controller.SenderController;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.ISenderService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class,
		RepositoryConfig.class, WebMvcConfig.class })
@TestPropertySource(value = "classpath:application-test-h2.properties")
@WebAppConfiguration
public class SenderControllerTests {

	private MockMvc mockMvc;

	@Mock
	private ISenderService senderService;

	@Autowired
	@InjectMocks
	private SenderController senderController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc =
			MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testList() throws Exception {
		final Sender sender1 = new Sender();
		sender1.setId(1L);
		sender1.setAddress("foo@bar.com");
		final Sender sender2 = new Sender();
		sender2.setId(2L);
		sender2.setAddress("bar@foo.org");

		when(senderService.list()).thenReturn(Arrays.asList(sender1, sender2));

		mockMvc.perform(get("/api/sender"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].address", is("foo@bar.com")))
			.andExpect(jsonPath("$[1].id", is(2)))
			.andExpect(jsonPath("$[1].address", is("bar@foo.org")));
		verify(senderService, times(1)).list();
		verifyNoMoreInteractions(senderService);
	}

	@Before
	public void initMockito() {
		MockitoAnnotations.initMocks(this);
	}
}
