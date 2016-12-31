package org.legurun.test.fakemailserver.controller;

import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/mail")
public class MailController {

//	@Autowired
//	private GreenMail greenMail;

	@GetMapping(value={"","/"})
	public String index() {
		return "redirect:/mail/list";
	}

	@GetMapping(value="list")
	public String list(Model model) throws MessagingException {
//		MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
//		for (MimeMessage message : receivedMessages) {
//			System.out.println(message.getSubject());
//		}
//		model.addAttribute("receivedMessages", receivedMessages);
		return "mail/list";
	}
	
}
