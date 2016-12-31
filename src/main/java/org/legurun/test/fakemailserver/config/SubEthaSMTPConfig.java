package org.legurun.test.fakemailserver.config;

import java.net.InetAddress;

import org.legurun.test.fakemailserver.mail.MailListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

@Component
@ComponentScan(basePackages="org.legurun.test.fakemailserver.mail")
public class SubEthaSMTPConfig {

	@Bean(initMethod="start", destroyMethod="stop")
	public SMTPServer smtpServer(MailListener mailListener) {
		MessageHandlerFactory factory = new SimpleMessageListenerAdapter(mailListener);
		SMTPServer server = new SMTPServer(factory);
		server.setBindAddress(InetAddress.getLoopbackAddress());
		server.setPort(10025);
		return server;
	}
/*
	@Bean
	public MailListener mailListener() {
		MailListener mailListener = new MailListener();
		return mailListener;
	}*/
}
