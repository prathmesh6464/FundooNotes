package com.bridz.controller;

import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.UserDetailsDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.response.Response;
import com.bridz.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginControl {

	@Autowired
	UserService userServiceObject;

	String secretWord;

	@GetMapping("/userLogin")
	public List<UserDetailsDto> userLogin(@Valid @RequestBody LoginDto userLoginDtoObject) {

		return userServiceObject.userLogin(userLoginDtoObject);
	}

	@RequestMapping(value = "/userRegistration", method = { RequestMethod.POST })
	public Response userRegistration(@Valid @RequestBody UserRegistrationDto userRegistrationDtoObject) {

		// Storing user data into data base
		Response response = userServiceObject.registerUser(userRegistrationDtoObject);

		return response;
	}

	@GetMapping("/forgetPassword")
	public Response forgetPassword(@Valid @RequestBody ForgetPasswordDto forgetPasswordDtoObject) {

		//Variable
		secretWord = forgetPasswordDtoObject.getSecretEmergencyWord();
		
		//Checking user information valid or not
		Response response = userServiceObject.forgetPassword(forgetPasswordDtoObject);

		return response;
	}

	@RequestMapping(value = "/resetPassword", method = { RequestMethod.PUT })
	public Response resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDtoObject) {

		return userServiceObject.resetPassword(resetPasswordDtoObject, secretWord);
	}

	// @RequestMapping(value = "/addNote", method = { RequestMethod.POST })
	// public String addUserNote(NotesData userNote) {
	//
	// // Storing user note data into data base
	// notesRepository.save(userNote);
	// return "Note added successfully";
	// }
	//
	// @RequestMapping(value = "/deleteNote", method = { RequestMethod.DELETE })
	// public String deleteUserNote(NotesData userNote) {
	//
	// // Storing user note data into data base
	// // notesRepository.delete();
	// return "Note deleted successfully";
	// }
}