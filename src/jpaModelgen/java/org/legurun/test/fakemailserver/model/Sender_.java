package org.legurun.test.fakemailserver.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sender.class)
public abstract class Sender_ extends org.legurun.test.fakemailserver.model.AbstractEntity_ {

	public static volatile SetAttribute<Sender, Email> emails;
	public static volatile SingularAttribute<Sender, String> address;

}

