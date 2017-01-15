package org.legurun.test.fakemailserver.config;

import java.net.InetAddress;

import org.legurun.test.fakemailserver.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

@Component
@ComponentScan(basePackages="org.legurun.test.fakemailserver.mail")
public class SubEthaSMTPConfig {
	private static final Logger LOG = LoggerFactory.getLogger(SubEthaSMTPConfig.class);

	@Autowired
	@Bean(initMethod="start", destroyMethod="stop")
	public SMTPServer smtpServer(IEmailService mailService) {
		LOG.trace("Initialisation smtpServer");
		MessageHandlerFactory factory = new SimpleMessageListenerAdapter(mailService);
		SMTPServer server = new SMTPServer(factory);
		server.setBindAddress(InetAddress.getLoopbackAddress());
		server.setPort(10025);
		return server;
	}
}
