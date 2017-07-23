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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.config.RepositoryConfig;
import org.legurun.test.fakemailserver.config.RootConfig;
import org.legurun.test.fakemailserver.dao.ISenderDao;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.ISenderService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

/**
 * Sender service tests.
 * @author patrice
 * @since 2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, RepositoryConfig.class })
@TestPropertySource(value = "classpath:application-test-h2.properties")
public class SenderServiceTests {
	/**
	 * Email service to test.
	 */
	@Autowired
	@InjectMocks
	private ISenderService senderService;

	@Mock
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

	@Before
	public void initMockito() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(senderService, "senderDao", senderDao);
	}
}
