package com.bridz.controller;
import com.bridz.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginControl {
	
	
	@GetMapping("/userLogin")
	public  LoginData userLogin(LoginData loginData) {

		return loginData;
	}

	@GetMapping("/userRegistration")
	public UserDetails userRegistration(UserDetails userData) {

		return userData;
	}
} 
