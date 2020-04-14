package com.bridz.controller;
import com.bridz.model.*;
import com.bridz.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginControl {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/userLogin")
	public  LoginData userLogin(LoginData loginData) {

		return loginData;
	}

	@GetMapping("/userRegistration")
	public String userRegistration(UserDetails userData) {

		userRepository.save(userData);
		return "Successfully uploaded";
	}
} 
