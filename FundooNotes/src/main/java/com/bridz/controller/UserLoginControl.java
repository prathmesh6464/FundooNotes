package com.bridz.controller;

import com.bridz.model.UserDetails;
import com.bridz.dto.LoginDataDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.SecretInformationDto;
import com.bridz.dto.UserDetailsDto;
import com.bridz.model.NotesData;
import com.bridz.repository.NotesRepository;
import com.bridz.repository.UserRepository;
import com.bridz.response.Response;
import com.bridz.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginControl {

	@Autowired
	UserService userServiceObject;

	String secretWord;

	@GetMapping("/userLogin")
	public List<UserDetails> userLogin(LoginDataDto userLoginDtoObject) {

		return userServiceObject.userLogin(userLoginDtoObject);
	}

	@RequestMapping(value = "/userRegistration", method = { RequestMethod.POST })
	public Response userRegistration(UserDetailsDto userDetailsDtoObject) {

		UserDetails userDetailsObject = new UserDetails(userDetailsDtoObject);
		
		// Storing user data into data base
		Response response = userServiceObject.registerUser(userDetailsObject);
		
		return response;
	}

	@GetMapping("/forgetPassword")
	public Response forgetPassword(SecretInformationDto secretInformationData) {

		secretWord = secretInformationData.getSecretEmergencyWord();
		Response response = userServiceObject.forgetPassword(secretInformationData);	
		
		return response;
	}

	@RequestMapping(value = "/resetPassword", method = { RequestMethod.PUT })
	public Response resetPassword(ResetPasswordDto resetPasswordDto) {
		
		return userServiceObject.resetPassword(resetPasswordDto, secretWord);	
	}

//	@RequestMapping(value = "/addNote", method = { RequestMethod.POST })
//	public String addUserNote(NotesData userNote) {
//
//		// Storing user note data into data base
//		notesRepository.save(userNote);
//		return "Note added successfully";
//	}
//
//	@RequestMapping(value = "/deleteNote", method = { RequestMethod.DELETE })
//	public String deleteUserNote(NotesData userNote) {
//
//		// Storing user note data into data base
//		// notesRepository.delete();
//		return "Note deleted successfully";
//	}
}
