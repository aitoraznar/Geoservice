package com.geonames.dao;

import java.util.List;

public interface GenericDAO<T> {

	 T findById(Long id);

	 List<T> findAll();

	 long countAll();

	 void persist(T model);

	 void remove(T model);

	 void removeAll();
}
