package org.legurun.test.fakemailserver.dao;

import java.util.List;

import org.legurun.test.fakemailserver.model.AbstractEntity;

public interface IDao<T extends AbstractEntity> {

	T get(Long id);

	void persist(T entity);

	void delete(T entity);

	List<T> list();
}
