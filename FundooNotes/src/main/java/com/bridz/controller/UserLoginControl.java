package com.bridz.controller;

import com.bridz.model.UserDetails;
import com.bridz.dto.LoginDataDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.SecretInformationDto;
import com.bridz.model.NotesData;
import com.bridz.repository.NotesRepository;
import com.bridz.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginControl {

	// Created object of user repository
	@Autowired
	UserRepository userRepository;

	@Autowired
	NotesRepository notesRepository;

	String secretWord;

	@GetMapping("/userLogin")
	public List<UserDetails> userLogin(LoginDataDto loginDataDto) {

		// Checking user name and password is valid or not
		if (userRepository.findByUserName(loginDataDto.getUserName())
				.equals(userRepository.findByPassword(loginDataDto.getPassword()))) {

			return userRepository.findByPassword(loginDataDto.getPassword());
		}

		return null;
	}

	@RequestMapping(value = "/userRegistration", method = { RequestMethod.POST })
	public String userRegistration(UserDetails userData) {

		// Storing user data into data base
		userRepository.save(userData);
		return "Successfully uploaded";
	}

	@GetMapping("/forgetPassword")
	public boolean forgetPassword(SecretInformationDto secretInformationData) {

		secretWord = secretInformationData.getSecretEmergencyWord();
		// Checking secret information is valid or not
		if (userRepository.findBySecretEmergencyWord(secretInformationData.getSecretEmergencyWord())
				.equals(userRepository.findByFirstMobileNumber(secretInformationData.getFirstMobileNumber())))
			return true;

		return false;
	}

	@RequestMapping(value = "/resetPassword", method = { RequestMethod.PUT })
	public String resetPassword(ResetPasswordDto resetPasswordDto) {

		// Storing user's edited information to data base
		if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {

			try {
				userRepository.setPassword(resetPasswordDto.getPassword(), secretWord);
			} catch (Exception e) {
				return "Successfully password changed";
			}
		}

		return "Confirm password and password not matched";
	}

	@RequestMapping(value = "/addNote", method = { RequestMethod.POST })
	public String addUserNote(NotesData userNote) {

		// Storing user note data into data base
		notesRepository.save(userNote);
		return "Note added successfully";
	}
	
	@RequestMapping(value = "/deleteNote", method = { RequestMethod.DELETE })
	public String deleteUserNote(NotesData userNote) {

		// Storing user note data into data base
		//notesRepository.delete();
		return "Note deleted successfully";
	}
}