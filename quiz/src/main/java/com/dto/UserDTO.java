package com.dto;

public class UserDTO {
	private String userUID;
	private String userName;
	private String userPassword;
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
	public UserDTO(String userUID, String userName, String userPassword) {
		super();
		this.userUID = userUID;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	public UserDTO() {
		
	}
	
}
