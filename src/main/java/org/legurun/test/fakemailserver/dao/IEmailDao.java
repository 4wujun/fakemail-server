package org.legurun.test.fakemailserver.dao;

import java.util.List;

import org.legurun.test.fakemailserver.dto.EmailSearchDTO;
import org.legurun.test.fakemailserver.model.Email;
import org.legurun.test.fakemailserver.utils.FilterExtjs;
import org.legurun.test.fakemailserver.utils.PagedList;

public interface IEmailDao extends IDao<Email> {
	public PagedList<EmailSearchDTO> search(List<FilterExtjs> filters, Integer start, Integer limit);
}