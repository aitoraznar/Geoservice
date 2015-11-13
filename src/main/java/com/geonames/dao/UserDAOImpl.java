package com.geonames.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.geonames.models.User;

public class UserDAOImpl implements UserDAO<User, String>  {
	
	@PersistenceContext(unitName = "mysql")
	private EntityManager entityManager;
	
	private Session currentSession;
	
	private Transaction currentTransaction;

	public UserDAOImpl() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public User persist(User entity) {
		getCurrentSession().save(entity);
		return entity;
	}

	public User update(User entity) {
		getCurrentSession().update(entity);
		return entity;
	}

	public User findById(String id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user; 
	}

	public void delete(User entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
		return users;
	}

	public void deleteAll() {
		List<User> entityList = findAll();
		for (User entity : entityList) {
			delete(entity);
		}
	}
	
	
	/*@PersistenceContext 
	private static EntityManager entityManager;
	
	public UserDAOImpl() {
	}
	
	public User persist (User user) {	
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
 
		try {
			entityManager.getTransaction().begin();
 
			GenericDAO<User> dao = new GenericDAOImpl<User>(User.class, entityManager);
			dao.persist(user);
 
			entityManager.getTransaction().commit();
			entityManager.flush();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
		
		return user;
	}
	
	public List<User> findAll () {
		List<User> items = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
 
		try {
			entityManager.getTransaction().begin();
 
			//GenericDAO<User> dao = new GenericDAOImpl<User>(User.class, entityManager);
			//items = dao.findAll();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			  
			 CriteriaQuery<User> cq = cb.createQuery(User.class);
			 cq.select(cq.from(User.class));
			  
			 items = entityManager.createQuery(cq).getResultList();
 
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
		
		return items;
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}*/
}

