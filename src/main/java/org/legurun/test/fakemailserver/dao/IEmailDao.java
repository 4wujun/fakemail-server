package org.legurun.test.fakemailserver.dao;

import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.legurun.test.fakemailserver.utils.SortOrder;

public interface IEmailDao extends IDao<Email> {
	public PagedList<EmailSearchReport> search(Sender sender,
		String recipient, Integer start, Integer limit,
		String sortProperty, SortOrder sortOrder);
}