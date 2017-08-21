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
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.dao.ISenderDao;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.ISenderService;
import org.legurun.test.fakemailserver.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

/**
 * Sender service tests.
 * @author patlenain
 * @since 2017
 */
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test-h2.properties")
public class SenderServiceTests {
	/**
	 * Email service to test.
	 */
	@Autowired
	private ISenderService senderService;

	@MockBean
	private ISenderDao senderDao;

	@Test
	public void testList() {
		final List<Sender> senders = new ArrayList<Sender>();
		final Sender sender = new Sender();
		sender.setAddress("foo@bar.com");
		senders.add(sender);
		when(senderDao.list()).thenReturn(senders);

		final List<Sender> result = senderService.list();
		assertEquals("Result list must contains only one result",
				senders, result);
		verify(senderDao, times(1)).list();
	}


	@Test
	public void testGet() {
		final Sender sender = new Sender();
		when(senderDao.get(1L)).thenReturn(null);
		when(senderDao.get(2L)).thenReturn(sender);

		final Sender resultNull = senderService.get(1L);
		assertNull("Result must be null", resultNull);

		final Sender resultNotNull = senderService.get(2L);
		assertNotNull("Result must be not null", resultNotNull);
		verify(senderDao, times(1)).get(eq(1L));
		verify(senderDao, times(1)).get(eq(2L));
	}

	@TestConfiguration
	static class TestContextConfiguration {
		@Bean
		public ISenderService senderService() {
			return new SenderService();
		}
	}
}
