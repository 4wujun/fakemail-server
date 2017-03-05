package org.legurun.test.fakemailserver.controller;

import java.util.List;

import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.service.ISenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sender")
public class SenderController {

	@Autowired
	private ISenderService senderService;

	@GetMapping
	public List<Sender> list() {
		return senderService.list();
	}

}
