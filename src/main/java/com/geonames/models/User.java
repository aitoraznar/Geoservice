package com.geonames.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="master")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name = "idmaster")
	private Integer idUser;
	
	//@Max(value=255)
	private String name;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private UserDetails userDetails;
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	public User() {
		
	}

	public User(String name, String zip) {
		this.setName(name);
		this.userDetails = new UserDetails(zip, "");
	}
	
	public boolean validate() {
		return !(this.getName() == null || this.getName().equals("")) && this.getUserDetails().validate();
	}
	
}
