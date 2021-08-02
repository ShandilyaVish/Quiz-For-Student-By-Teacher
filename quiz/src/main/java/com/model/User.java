package com.model;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.NonNull;


@Entity
public class User {
	@Id
	private String userUID;
	@NonNull
	private String userName;
	@NonNull
	private String userPassword;
	private String roles;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String userUID, String userName, String userPassword, String roles) {
		super();
		this.userUID = userUID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.roles = roles;
	}
	public String getUserUID() {
		return userUID;
	}
	public void setUserUID(String userUID) {
		this.userUID = userUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	

}
