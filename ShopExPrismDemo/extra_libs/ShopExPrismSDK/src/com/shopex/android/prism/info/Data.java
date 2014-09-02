package com.shopex.android.prism.info;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Data implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2620950902677311500L;

	@SerializedName("@id")
	private String aId;
	
	private String login;
	
	private String email;
	
	private String firstname;
	
	private String lastname;

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
}
