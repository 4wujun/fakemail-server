package org.legurun.test.fakemailserver.dao;

import java.util.Date;

import org.legurun.test.fakemailserver.dto.EmailSearchReport;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.utils.PagedList;
import org.legurun.test.fakemailserver.utils.SortOrder;

public interface IEmailDao extends IDao<Email> {
	public PagedList<EmailSearchReport> search(Sender sender,
		String recipient, Date sentSince, Date sentBefore,
		Integer start, Integer limit,
		String sortProperty, SortOrder sortOrder);
}