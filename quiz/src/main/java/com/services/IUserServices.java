package com.services;

import com.dto.UserDTO;
import com.model.User;

public interface IUserServices {
	public boolean saveTheUser(UserDTO userDTO,String roles);
	public User findUserById(String userUID);
}
