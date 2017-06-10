package org.legurun.test.fakemailserver.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.legurun.test.fakemailserver.config.RepositoryConfig;
import org.legurun.test.fakemailserver.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, RepositoryConfig.class })
public class EmailServiceTests {
	@Autowired
	private IEmailService emailService;

	@Test
	public void testAccept() {
		Assert.isTrue(
				emailService.accept("sender@foo.com", "recipient@bar.org"),
				"accept() must be true");
	}
}
