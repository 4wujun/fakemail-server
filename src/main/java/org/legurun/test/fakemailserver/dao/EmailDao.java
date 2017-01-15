package org.legurun.test.fakemailserver.dao;

import org.legurun.test.fakemailserver.model.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDao extends AbstractDao<Email> implements IEmailDao {

}
