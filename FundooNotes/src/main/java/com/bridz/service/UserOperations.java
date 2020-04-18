package com.bridz.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bridz.model.LoginData;
import com.bridz.model.ResetPasswordData;
import com.bridz.model.SecretInformation;
import com.bridz.model.UserDetails;
import com.bridz.repository.UserRepository;
import com.bridz.response.Response;

@Service
public class UserOperations implements UserService {

	private UserRepository userRepository;

	public UserOperations(UserRepository userRepository) {

		super();
		this.userRepository = userRepository;
	}

	@Override
	public Response registerUser(UserDetails userDetails) {

		userRepository.save(userDetails);
		return new Response("User details saved", 200);
	}

	@Override
	public List<UserDetails> userLogin(LoginData userLoginObject) {

		// Checking user name and password is valid or not
		if (userRepository.findByUserName(userLoginObject.getUserName())
				.equals(userRepository.findByPassword(userLoginObject.getPassword()))) {

			return userRepository.findByPassword(userLoginObject.getPassword());
		}

		return null;
	}

	@Override
	public Response forgetPassword(SecretInformation secretInformationData) {

		// Checking secret information is valid or not
		if (userRepository.findBySecretEmergencyWord(secretInformationData.getSecretEmergencyWord())
				.equals(userRepository.findByFirstMobileNumber(secretInformationData.getFirstMobileNumber())))
			return new Response("true", 200);

		return new Response("false", 201);
	}
 
	@Override
	public Response resetPassword(ResetPasswordData resetPassword, String secretWord) {

		// Storing user's edited information to data base
		if (resetPassword.getPassword().equals(resetPassword.getConfirmPassword())) {

			try {
				
				userRepository.setPassword(resetPassword.getPassword(), secretWord);
			} catch (Exception e) {
				return new Response("Successfully password changed", 200);
			}
		}

		return new Response("Confirm password and password not matched", 201);
	}
}
