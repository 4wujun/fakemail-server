package org.legurun.test.fakemailserver.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.legurun.test.fakemailserver.utils.SortOrder;
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

	protected Criteria createCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	public T get(Long id) {
		return getSession().get(persistentClass, id);
	}

	@Override
	public void persist(T entity) {
		getSession().persist(entity);
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list() {
		return createCriteria().list();
	}

	protected static Order getOrder(String sortProperty, SortOrder sortOrder) {
		if (sortOrder == SortOrder.DESCENDING) {
			return Order.desc(sortProperty);
		}
		else {
			return Order.asc(sortProperty);
		}
	}
}
