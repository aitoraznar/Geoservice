package com.geonames.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	 private Class<T> clazz;
	 private EntityManager entityManager;

	 public GenericDAOImpl(Class<T> clazz, EntityManager entityManager) {
		  this.clazz = clazz;
		  this.entityManager = entityManager;
	 }

	 @Override
	 public T findById(Long id) {
		  return entityManager.find(clazz, id);
	 }

	 @Override
	 public List<T> findAll() {
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		  
		 CriteriaQuery<T> cq = cb.createQuery(clazz);
		 cq.select(cq.from(clazz));
		  
		 return entityManager.createQuery(cq).getResultList();
	 }

	 @Override
	 public long countAll() {
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		  CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		  cq.select(cb.count(cq.from(clazz)));

		  return entityManager.createQuery(cq).getSingleResult().intValue();
	 }

	 @Override
	 public void persist(T object) {
		  entityManager.persist(object);
	 }

	 @Override
	 public void remove(T object) {
		  entityManager.remove(object);
	 }

	 @Override
	 public void removeAll() {
		  String jpql = String.format("delete from %s", clazz.getName());
		  Query query = entityManager.createQuery(jpql);
		  query.executeUpdate();
	 }
}