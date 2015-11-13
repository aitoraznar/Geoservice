package com.geonames.controllers.handlers;

import org.geonames.Toponym;

import com.geonames.models.User;
import com.geonames.services.UserService;
import com.geonames.utils.Utils;

public class GeoControllerHandler {
	
	public static UserService userService;
	
	public static UserService getUserService () {
		return new UserService();
	}
	
	public static String handleUserLocate(String name, String zip) {
		userService = getUserService();
		
		User user = new User(name, zip);
    	if (user.validate()) {
    		Toponym toponym = UserService.searchUserLocation(user);// Get The city
    		user.getUserDetails().setCity(toponym.getName()); 
    		
    		User savedUser = userService.persist(user);
        	
        	return Utils.jr(200, "saved", savedUser);
    	} else {
    		return Utils.jr(422, "Unprocessable entity", "Faltan datos");
    	}
	}
}
