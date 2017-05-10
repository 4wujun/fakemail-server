package org.legurun.test.fakemailserver.service;

import java.util.List;

import org.legurun.test.fakemailserver.model.Sender;

public interface ISenderService {

	Sender get(Long id);

	List<Sender> list();
}
