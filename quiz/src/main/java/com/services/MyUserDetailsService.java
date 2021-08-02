package com.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findByUserName(userName);
		System.out.println(user.get().getUserName() + " " + user.get().getRoles());
		log.debug(user.get().getUserName());
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not found  : " + userName));
		
		return user.map(MyUserDetails::new).get();
	}

}
