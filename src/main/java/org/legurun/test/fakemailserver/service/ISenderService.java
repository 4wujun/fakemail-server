package org.legurun.test.fakemailserver.service;

import java.util.List;

import org.legurun.test.fakemailserver.model.Sender;

public interface ISenderService {

	public Sender get(Long id);

	public List<Sender> list();

}