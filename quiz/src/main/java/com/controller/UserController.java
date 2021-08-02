package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDTO;
import com.services.IUserServices;
import com.utils.Utils;

@RestController
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	IUserServices userServices;
	
	@PostMapping("createUser")
	public ResponseEntity<String> createNewStudent(@RequestBody UserDTO newStudent,
			@RequestParam String userRole) {
		boolean canUserBeSaved = userServices.saveTheUser(newStudent, userRole);
		if(canUserBeSaved) {
			return new ResponseEntity<>(Utils.SAVED,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Utils.ALREADY_PRESENT, HttpStatus.CONFLICT);
	}
}
