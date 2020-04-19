package com.bridz.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.SecretInformationDto;
import com.bridz.dto.UserDetailsDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.model.UserDetails;
import com.bridz.repository.UserRepository;
import com.bridz.response.Response;

@Service
public class UserOperations implements UserService {

	// Creating Object of model mapper
	ModelMapper modelMapperObject = new ModelMapper();

	// Creating Object of user Details Entity
	UserDetails userDetailsObject = new UserDetails();

	// User repository object
	private UserRepository userRepository;

	// Constructor
	public UserOperations(UserRepository userRepository) {

		super();
		this.userRepository = userRepository;
	}

	@Override
	public Response registerUser(UserRegistrationDto userRegistrationDtoObject) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(userRegistrationDtoObject, userDetailsObject);
		userRepository.save(userDetailsObject);

		return new Response("User details saved", 200);
	}

	@Override
	public List<UserDetailsDto> userLogin(LoginDto userLoginDtoObject) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(userLoginDtoObject, userDetailsObject);

		// Checking user name and password is valid or not
		if (userRepository.findByUserName(userDetailsObject.getUserName())
				.equals(userRepository.findByPassword(userDetailsObject.getPassword()))) {

			// copy variables from user details to user details data transfer object
			List<UserDetails> userDetailsListObject = userRepository.findByPassword(userDetailsObject.getPassword());
			List<UserDetailsDto> userDetailsDtoObject = new ArrayList<UserDetailsDto>();
			userDetailsDtoObject.add(new UserDetailsDto());

			// Using model mapper mapping dto object with user details entity
			modelMapperObject.map(userDetailsListObject.get(0), userDetailsDtoObject.get(0));

			return userDetailsDtoObject;
		}

		return null;
	}

	@Override
	public Response forgetPassword(SecretInformationDto secretInformationDtoObject) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(secretInformationDtoObject, userDetailsObject);

		// Checking secret information is valid or not
		if (userRepository.findBySecretEmergencyWord(userDetailsObject.getSecretEmergencyWord())
				.equals(userRepository.findByFirstMobileNumber(userDetailsObject.getFirstMobileNumber())))
			return new Response("true", 200);

		return new Response("false", 201);
	}

	@Override
	public Response resetPassword(ResetPasswordDto resetPasswordDtoObject, String secretWord) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(resetPasswordDtoObject, userDetailsObject);

		// Storing user's edited information to data base
		if (resetPasswordDtoObject.getPassword().equals(resetPasswordDtoObject.getConfirmPassword())) {

			try {

				userRepository.setPassword(userDetailsObject.getPassword(), secretWord);
			} catch (Exception e) {
				return new Response("Successfully password changed", 200);
			}
		}

		return new Response("Confirm password and password not matched", 201);
	}
}
