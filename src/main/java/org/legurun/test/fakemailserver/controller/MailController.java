package org.legurun.test.fakemailserver.controller;

import java.io.IOException;

import org.legurun.test.fakemailserver.dto.EmailSearchDTO;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.legurun.test.fakemailserver.service.ISenderService;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api/mail")
public class MailController {
	private static final Logger LOG = LoggerFactory.getLogger(MailController.class);

	@Autowired
	private ISenderService senderService;

	@Autowired
	private IEmailService emailService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagedList<EmailSearchDTO> search(@RequestParam("senderId") Long senderId,
			@RequestParam("start") Integer start, @RequestParam("limit") Integer limit) throws JsonMappingException, IOException {
		Sender sender = null;
		if (senderId != null) {
			sender = senderService.get(senderId);
		}
		return emailService.search(sender, start, limit);
	}
}
