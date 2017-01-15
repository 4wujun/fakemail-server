package org.legurun.test.fakemailserver.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T extends Serializable> implements IDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * @see org.legurun.test.fakemailserver.dao.IDao#persist(T)
	 */
	@Override
	public void persist(T entity) {
		getSession().persist(entity);
	}

	/* (non-Javadoc)
	 * @see org.legurun.test.fakemailserver.dao.IDao#delete(T)
	 */
	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	/* (non-Javadoc)
	 * @see org.legurun.test.fakemailserver.dao.IDao#list()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> list() {
		return getSession().createCriteria(persistentClass).list();
	}

	protected Criteria createCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}
