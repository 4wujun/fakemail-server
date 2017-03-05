package org.legurun.test.fakemailserver.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T extends Serializable> {

	T get(Long id);

	void persist(T entity);

	void delete(T entity);

	List<T> list();
}