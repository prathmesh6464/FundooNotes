package com.bridz.controller;

import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.response.Response;
import com.bridz.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginControl {

	@Autowired
	UserService userServiceObject;

	String emailId;

	@GetMapping("/userLogin")
	public Response userLogin(@Valid @RequestBody LoginDto userLoginDtoObject) {

		return userServiceObject.userLogin(userLoginDtoObject);
	}

	@PostMapping("/userRegistration")
	public Response userRegistration(@Valid @RequestBody UserRegistrationDto userRegistrationDtoObject) {

		// Storing user data into data base
		Response response = userServiceObject.registerUser(userRegistrationDtoObject);

		return response;
	}

	@GetMapping("/forgetPassword")
	public ResponseEntity<String> forgetPassword(@Valid @RequestBody ForgetPasswordDto forgetPasswordDtoObject) {

		return userServiceObject.forgetPassword(forgetPasswordDtoObject);
	}

	@PutMapping("/resetPassword/{token}/{emailId}")
	public Response resetPassword(@Valid @PathVariable("token") String token, @PathVariable("emailId") String emailId,
			@RequestBody ResetPasswordDto resetPasswordDtoObject) {

		return userServiceObject.resetPassword(resetPasswordDtoObject, emailId);
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