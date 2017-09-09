package org.legurun.test.fakemailserver.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Email.class)
public abstract class Email_ extends org.legurun.test.fakemailserver.model.AbstractEntity_ {

	public static volatile SingularAttribute<Email, Date> sentDate;
	public static volatile SingularAttribute<Email, Sender> sender;
	public static volatile SingularAttribute<Email, String> subject;
	public static volatile SingularAttribute<Email, String> recipient;
	public static volatile SingularAttribute<Email, byte[]> message;

}

