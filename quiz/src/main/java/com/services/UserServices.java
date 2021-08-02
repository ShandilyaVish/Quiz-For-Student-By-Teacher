package com.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.UserDTO;
import com.model.User;
import com.repository.UserRepository;

@Service
public class UserServices implements IUserServices{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public boolean saveTheUser(UserDTO userDTO,String roles) {
		boolean isUserAlreadyPresent = checkIfUserPresent(userDTO);
		if(isUserAlreadyPresent) {
			return false;
		}
		
		User newUser = modelMapper.map(userDTO, User.class);
		newUser.setRoles(roles);
		userRepository.save(newUser);
		return true;
	}

	private boolean checkIfUserPresent(UserDTO userDTO) {
		Optional<User> isUserPresent = userRepository.
				findById(userDTO.getUserUID());
		if(isUserPresent.isEmpty()) {
			Optional<User> isUserNameSamePresent = userRepository.findByUserName(userDTO.getUserName());
			if(isUserNameSamePresent.isEmpty()) {
				return false;
			}
		}

		
		return true;
	}

	@Override
	public User findUserById(String userUID) {
		return userRepository.getById(userUID);
	}
}
