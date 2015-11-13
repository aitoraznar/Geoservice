package com.geonames.services;

import java.util.List;

import org.geonames.PostalCode;
import org.geonames.PostalCodeSearchCriteria;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import com.geonames.dao.UserDAOImpl;
import com.geonames.models.User;

public class UserService {
	private static String geonamesUsername = "renaitor"; // TODO Get from properties
	
	private static UserDAOImpl userDAO;
	
    public UserService() {
    	userDAO = new UserDAOImpl();
    }
    
    public UserDAOImpl userDAO() {
		return userDAO;
	}
    
    public User persist(User entity) {
		userDAO.openCurrentSessionwithTransaction();
		userDAO.persist(entity);
		userDAO.closeCurrentSessionwithTransaction();
		return entity;
	}

	public User update(User entity) {
		userDAO.openCurrentSessionwithTransaction();
		userDAO.update(entity);
		userDAO.closeCurrentSessionwithTransaction();
		return entity;
	}

	public User findById(String id) {
		userDAO.openCurrentSession();
		User user = userDAO.findById(id);
		userDAO.closeCurrentSession();
		return user;
	}

	public User delete(String id) {
		userDAO.openCurrentSessionwithTransaction();
		User user = userDAO.findById(id);
		userDAO.delete(user);
		userDAO.closeCurrentSessionwithTransaction();
		return user;
	}

	public List<User> findAll() {
		userDAO.openCurrentSession();
		List<User> users = userDAO.findAll();
		userDAO.closeCurrentSession();
		return users;
	}

	public void deleteAll() {
		userDAO.openCurrentSessionwithTransaction();
		userDAO.deleteAll();
		userDAO.closeCurrentSessionwithTransaction();
	}

	

	
	public static PostalCode postalCodeSearch (User user) {
		WebService.setUserName(geonamesUsername);
		 
		PostalCodeSearchCriteria searchCriteria = new PostalCodeSearchCriteria();
		searchCriteria.setPostalCode(user.getUserDetails().getZip());
		List<PostalCode> searchResult = null;
		try {
			searchResult = WebService.postalCodeSearch(searchCriteria);
			System.out.println("searchResult " + searchResult);
		} catch (Exception e) {
			System.out.println("ERROR searchResult");
			e.printStackTrace();
		}
		if (searchResult != null) {
			for (PostalCode postalCode: searchResult) {
				System.out.println(postalCode.getPlaceName());
			}
		}
		  
		return searchResult.get(0); 
	}
	
	public static Toponym searchUserLocation (User user) {
		WebService.setUserName(geonamesUsername);
		 
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ(user.getUserDetails().getZip());
		ToponymSearchResult searchResult = null;
		try {
			searchResult = WebService.search(searchCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (searchResult != null && searchResult.getToponyms().size() > 0) {
			return searchResult.getToponyms().get(0);

		} else {
			return null;
		}
	}
		
}
