package org.legurun.test.fakemailserver.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.legurun.test.fakemailserver.model.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T extends AbstractEntity> implements IDao<T> {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public T get(final Long id) {
		return entityManager.find(persistentClass, id);
	}

	@Override
	public void persist(final T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void delete(final T entity) {
		entityManager.remove(entity);
	}

	@Override
	public List<T> list() {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(this.persistentClass);
		query.from(this.persistentClass);
		return this.getEntityManager().createQuery(query).getResultList();
	}
}
