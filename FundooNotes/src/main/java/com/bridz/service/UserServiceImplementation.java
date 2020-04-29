package com.bridz.service;

import java.util.Collections;
import javax.management.JMException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.LoginDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.exception.JmsException;
import com.bridz.exception.JwtTokenException;
import com.bridz.exception.UserException;
import com.bridz.model.UserDetails;
import com.bridz.repository.UserRepository;
import com.bridz.utility.JwtToken;
import com.bridz.utility.EmailService;

@Service
public class UserServiceImplementation implements UserService {

	// Creating Object of model mapper]
	@Autowired
	private ModelMapper modelMapperObject;

	// Creating Object of user Details Entity
	@Autowired
	private UserDetails userDetailsEntityObject;

	// User repository object
	@Autowired
	private UserRepository userRepository;

	@Autowired
	Environment environmentObject;

	// Jwt token object created
	@Autowired
	private JwtToken jwtTokenObject;

	// Email service object created
	@Autowired
	private EmailService emailServiceObject;

	// Token variable for verification of token
	private String token;

	// User registration dto object used in user verification
	private UserRegistrationDto userRegistrationObject;

	@Override
	public ResponseEntity<String> registerUser(UserRegistrationDto userRegisterDtoObject) {

		// User registration object used in user Verification
		userRegistrationObject = userRegisterDtoObject;

		// Email related variables
		String to = userRegisterDtoObject.getEmailId();
		String subject = "Authentication of new registered user";
		token = jwtTokenObject.generateToken(userRegisterDtoObject);

		// User verification url
		String userVerificationUrl = "http://localhost:8081/userVerification/" + token;

		// Send email method called
		try {

			emailServiceObject.send(to, subject, userVerificationUrl);
		} catch (JMException e) {

			throw new JmsException(Integer.parseInt("status.jms.send.mail.errorCode"),
					environmentObject.getProperty("status.jms.send.mail.errorMessage"));
		}

		return new ResponseEntity<String>(environmentObject.getProperty("status.success.user.register"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> userLogin(LoginDto userLoginDtoObject) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(userLoginDtoObject, userDetailsEntityObject);

		// Checking user name and password is valid or not
		if (!userRepository.findByUserName(userDetailsEntityObject.getUserName()).equals(Collections.<String>emptyList())
				&& (!userRepository.findByPassword(userDetailsEntityObject.getPassword())
						.equals(Collections.<String>emptyList()))
				&& userRepository.findByUserName(userDetailsEntityObject.getUserName())
						.equals(userRepository.findByPassword(userDetailsEntityObject.getPassword()))) {

			return new ResponseEntity<String>(environmentObject.getProperty("status.success.user.login"),
					HttpStatus.OK);
		}

		throw new UserException(Integer.parseInt(environmentObject.getProperty("status.user.authentication.errorCode")),
				environmentObject.getProperty("status.user.authentication.errorMessage"));
	}

	@Override
	public ResponseEntity<String> forgetPassword(ForgetPasswordDto forgetPasswordDtoObject) {

		// Email related variables
		String to = forgetPasswordDtoObject.getEmailId();
		String subject = "Authentication";
		String token = jwtTokenObject.generateToken(forgetPasswordDtoObject);
		String emailId = forgetPasswordDtoObject.getEmailId();

		// Reset url
		String resetPasswordUrl = "http://localhost:8081/resetPassword/" + token + "/" + emailId;

		try {

			// Send email method called
			emailServiceObject.send(to, subject, resetPasswordUrl);
		} catch (JMException e) {

			throw new JmsException(

					Integer.parseInt(environmentObject.getProperty("status.jms.send.mail.errorCode")),
					environmentObject.getProperty("status.jms.send.mail.errorMessage"));
		}

		return new ResponseEntity<String>(environmentObject.getProperty("status.success.user.forgetpassword"),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> resetPassword(ResetPasswordDto resetPasswordDtoObject, String emailId) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(resetPasswordDtoObject, userDetailsEntityObject);

		// Storing user's edited information to data base
		if (resetPasswordDtoObject.getPassword().equals(resetPasswordDtoObject.getConfirmPassword())) {

			try {

				userRepository.setPassword(userDetailsEntityObject.getPassword(), emailId);
			} catch (Exception e) {

				return new ResponseEntity<String>(environmentObject.getProperty("status.success.user.resetPassword"),
						HttpStatus.OK);
			}
		}

		throw new UserException(
				Integer.parseInt(environmentObject.getProperty("status.user.authentication.password.errorCode")),
				environmentObject.getProperty("status.user.authentication.password.errorMessage"));
	}

	@Override
	public ResponseEntity<String> userVerification(String emailToken) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(userRegistrationObject, userDetailsEntityObject);

		// Checking system generated token and email send token is equal or not
		if (emailToken.equals(token)) {

			// saving user data into database
			userRepository.save(userDetailsEntityObject);

			return new ResponseEntity<String>(environmentObject.getProperty("status.success.user.verification"),
					HttpStatus.OK);
		}

		throw new JwtTokenException(Integer.parseInt(environmentObject.getProperty("status.jwt.token.match.errorCode")),
				environmentObject.getProperty("status.jwt.token.match.errorMessage"));
	}

}