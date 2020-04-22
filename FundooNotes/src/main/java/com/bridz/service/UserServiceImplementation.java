package com.bridz.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.LoginDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.model.UserDetails;
import com.bridz.repository.UserRepository;
import com.bridz.response.Response;
import com.bridz.utility.JwtToken;
import com.bridz.utility.EmailService;

@Service
public class UserServiceImplementation implements UserService {

	// Creating Object of model mapper
	ModelMapper modelMapperObject = new ModelMapper();

	// Creating Object of user Details Entity
	UserDetails userDetailsObject = new UserDetails();

	// User repository object
	private UserRepository userRepository;

	// Jwt token object created
	@Autowired
	JwtToken jwtTokenObject;

	// Email service object created
	@Autowired
	EmailService emailServiceObject;

	// Constructor
	public UserServiceImplementation(UserRepository userRepository) {

		super();
		this.userRepository = userRepository;
	}

	@Transactional
	@Override
	public Response registerUser(UserRegistrationDto userRegistrationDtoObject) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(userRegistrationDtoObject, userDetailsObject);
		userRepository.save(userDetailsObject);

		return new Response("User details saved", 200);
	}

	@Transactional
	@Override
	public Response userLogin(LoginDto userLoginDtoObject) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(userLoginDtoObject, userDetailsObject);

		// Checking user name and password is valid or not
		if (userRepository.findByUserName(userDetailsObject.getUserName())
				.equals(userRepository.findByPassword(userDetailsObject.getPassword()))) {

			return new Response("User Loged in successfully", 200);
		}

		return new Response("User name password not matched", 202);
	}

	@Transactional
	@Override
	public ResponseEntity<String> forgetPassword(ForgetPasswordDto forgetPasswordDtoObject) {

		//Email related variables
		String to = "requestchecking@gmail.com";
		String subject = "Authentication";
		String token = jwtTokenObject.generateToken(forgetPasswordDtoObject);
		
		//Reset url
		String resetPasswordUrl = "http://localhost:8081/resetPassword/" + token;

		//Send email method callled
		emailServiceObject.send(to, subject, resetPasswordUrl);
		
		return new ResponseEntity<String>("User authenticated", HttpStatus.OK);
	}

	@Transactional
	@Override
	public Response resetPassword(ResetPasswordDto resetPasswordDtoObject, String emailId) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(resetPasswordDtoObject, userDetailsObject);

		// Storing user's edited information to data base
		if (resetPasswordDtoObject.getPassword().equals(resetPasswordDtoObject.getConfirmPassword())) {

			try {

				userRepository.setPassword(userDetailsObject.getPassword(), emailId);
			} catch (Exception e) {
				return new Response("Successfully password changed", 200);
			}
		}

		return new Response("Confirm password and password not matched", 201);
	}

}
