package org.legurun.test.fakemailserver.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/mail")
public class MailController {

	@Autowired
	private IEmailService emailService;

	@GetMapping(value = { "", "/" })
	public String index() {
		return "redirect:/mail/list";
	}

	@GetMapping(value = "list")
	public String list(Model model) throws MessagingException {
		// MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
		// for (MimeMessage message : receivedMessages) {
		// System.out.println(message.getSubject());
		// }
		// model.addAttribute("receivedMessages", receivedMessages);
		List<Email> emails = emailService.list();
		for (Email email : emails) {
			System.out.println(email.getId());
		}
		return "mail/list";
	}

}
