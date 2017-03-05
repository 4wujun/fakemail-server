package org.legurun.test.fakemailserver.controller;

import java.io.IOException;
import java.util.List;

import org.legurun.test.fakemailserver.dto.EmailSearchDTO;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.legurun.test.fakemailserver.service.IExtjsService;
import org.legurun.test.fakemailserver.utils.FilterExtjs;
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
	private IEmailService emailService;

	@Autowired
	private IExtjsService extjsService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagedList<EmailSearchDTO> search(@RequestParam("filter") String filtersString,
			@RequestParam("start") Integer start, @RequestParam("limit") Integer limit) throws JsonMappingException, IOException {
		List<FilterExtjs> filters = extjsService.convert(filtersString);
		return emailService.search(filters, start, limit);
	}
}
