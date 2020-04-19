package com.bridz.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.SecretInformationDto;
import com.bridz.dto.UserDetailsDto;
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
	public List<UserDetailsDto> userLogin(LoginDto userLoginDtoObject) {

		// Checking user name and password is valid or not
		if (userRepository.findByUserName(userLoginDtoObject.getUserName())
				.equals(userRepository.findByPassword(userLoginDtoObject.getPassword()))) {

			// copy variables from user details to user details data transfer object
			List<UserDetails> userDetailsObject = userRepository.findByPassword(userLoginDtoObject.getPassword());
			List<UserDetailsDto> userDetailsDtoObject = new ArrayList<UserDetailsDto>();
			userDetailsDtoObject.add(new UserDetailsDto());
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(userDetailsObject.get(0), userDetailsDtoObject.get(0));

			return userDetailsDtoObject;
		}

		return null;
	}

	@Override
	public Response forgetPassword(SecretInformationDto secretInformationDtoObject) {

		// Checking secret information is valid or not
		if (userRepository.findBySecretEmergencyWord(secretInformationDtoObject.getSecretEmergencyWord())
				.equals(userRepository.findByFirstMobileNumber(secretInformationDtoObject.getFirstMobileNumber())))
			return new Response("true", 200);

		return new Response("false", 201);
	}

	@Override
	public Response resetPassword(ResetPasswordDto resetPasswordDtoObject, String secretWord) {

		// Storing user's edited information to data base
		if (resetPasswordDtoObject.getPassword().equals(resetPasswordDtoObject.getConfirmPassword())) {

			try {

				userRepository.setPassword(resetPasswordDtoObject.getPassword(), secretWord);
			} catch (Exception e) {
				return new Response("Successfully password changed", 200);
			}
		}

		return new Response("Confirm password and password not matched", 201);
	}
}
