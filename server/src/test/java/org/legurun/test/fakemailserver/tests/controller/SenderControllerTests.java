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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.controller.SenderController;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.ISenderService;
import org.legurun.test.fakemailserver.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@AutoConfigureDataJpa
@WebMvcTest(SenderController.class)
public class SenderControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ISenderService senderService;

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


	@TestConfiguration
	static class TestContextConfiguration {
		@Bean
		public ISenderService senderService() {
			return new SenderService();
		}
	}
}
