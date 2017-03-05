package org.legurun.test.fakemailserver.dao;

import org.legurun.test.fakemailserver.dto.EmailSearchDTO;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.model.Sender;
import org.legurun.test.fakemailserver.utils.PagedList;

public interface IEmailDao extends IDao<Email> {
	public PagedList<EmailSearchDTO> search(Sender sender, Integer start, Integer limit);
}