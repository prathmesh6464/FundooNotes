package com.bridz.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridz.dto.LoginDataDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.SecretInformationDto;
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
	public List<UserDetails> userLogin(LoginDataDto userLoginDtoObject) {

		// Checking user name and password is valid or not
		if (userRepository.findByUserName(userLoginDtoObject.getUserName())
				.equals(userRepository.findByPassword(userLoginDtoObject.getPassword()))) {

			return userRepository.findByPassword(userLoginDtoObject.getPassword());
		}

		return null;
	}

	@Override
	public Response forgetPassword(SecretInformationDto secretInformationData) {

		// Checking secret information is valid or not
		if (userRepository.findBySecretEmergencyWord(secretInformationData.getSecretEmergencyWord())
				.equals(userRepository.findByFirstMobileNumber(secretInformationData.getFirstMobileNumber())))
			return new Response("true", 200);

		return new Response("false", 201);
	}

	@Override
	public Response resetPassword(ResetPasswordDto resetPasswordDto, String secretWord) {

		// Storing user's edited information to data base
		if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {

			try {
				
				userRepository.setPassword(resetPasswordDto.getPassword(), secretWord);
			} catch (Exception e) {
				return new Response("Successfully password changed", 200);
			}
		}

		return new Response("Confirm password and password not matched", 201);
	}
} 
