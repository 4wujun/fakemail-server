package org.legurun.test.fakemailserver.dao;

import org.legurun.test.fakemailserver.model.Sender;

public interface ISenderDao extends IDao<Sender> {

	public Sender findByAddress(String address);
}