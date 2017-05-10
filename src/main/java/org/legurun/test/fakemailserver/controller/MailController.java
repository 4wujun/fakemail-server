package org.legurun.test.fakemailserver.controller;

import org.legurun.test.fakemailserver.dto.EmailSearchCommand;
import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.service.IEmailService;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.legurun.test.fakemailserver.utils.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(MailController.class);

	@Autowired
	private IEmailService emailService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagedList<EmailSearchReport> search(
			final EmailSearchCommand searchCommand,
			@RequestParam(value = "offset", required = false) final Integer offset,
			@RequestParam(value = "limit", required = false) final Integer limit,
			@RequestParam(value = "sort", required = false) final String sort,
			@RequestParam(value = "order", required = false) final SortOrder order) {
		LOG.debug("Params : since {} - before {}", searchCommand.getSentSince(), searchCommand.getSentBefore());
		return emailService.search(searchCommand, offset, limit, sort, order);
	}
}
