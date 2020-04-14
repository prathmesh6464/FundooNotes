package com.bridz.controller;
import com.bridz.model.UserDetails;
import com.bridz.model.LoginData;
import com.bridz.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserLoginControl {
	
	//Created object of user repository
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/userLogin")
	public  List<UserDetails> userLogin(LoginData loginData) {
	
		//Checking user name and password is valid or not
		if (userRepository.findByUserName(loginData.getUserName()).equals
				(userRepository.findByPassword(loginData.getPassword()))) {
			
			return userRepository.findByPassword(loginData.getPassword());
		}
		
		return null;
	}

	@GetMapping("/userRegistration")
	public String userRegistration(UserDetails userData) {

		//Storing user data into data base
		userRepository.save(userData);
		return "Successfully uploaded";
	}
} 
