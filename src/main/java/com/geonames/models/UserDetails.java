package com.geonames.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.geonames.utils.Utils;

@Entity
@Table(name="detalle")
public class UserDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "iddetalle")
	private Integer idUserDetails;
	
	//@Max(value=5)
	private String zip;
	
	//@Max(value=255)
	private String city;
	
	@OneToOne
	@JoinColumn(name = "master_idmaster")
	private User user;
	
	public Integer getIdUserDetails() {
		return idUserDetails;
	}

	public void setIdUserDetails(Integer idUserDetails) {
		this.idUserDetails = idUserDetails;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDetails() {
		
	}

	public UserDetails(String zip, String city) {
		this.setZip(zip);
		this.setCity(city);
	}
	
	public boolean validate() {
		return !(this.getZip() == null || !Utils.isNumeric(this.getZip())  || this.getZip().equals(""));
	}
}
