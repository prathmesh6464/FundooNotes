package com.bridz.controller;

import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserService userServiceObject;

	@GetMapping("/userLogin")
	public ResponseEntity<String> userLogin(@Valid @RequestBody LoginDto userLoginDtoObject) {

		return userServiceObject.userLogin(userLoginDtoObject);
	}

	@PostMapping("/userRegistration")
	public ResponseEntity<String> userRegistration(@Valid @RequestBody UserRegistrationDto userRegistrationDtoObject) {

		return userServiceObject.registerUser(userRegistrationDtoObject);
	}

	@GetMapping("/forgetPassword")
	public ResponseEntity<String> forgetPassword(@Valid @RequestBody ForgetPasswordDto forgetPasswordDtoObject) {

		return userServiceObject.forgetPassword(forgetPasswordDtoObject);
	}

	@PutMapping("/resetPassword/{token}/{emailId}")
	public ResponseEntity<String> resetPassword(@PathVariable("token") String token,
			@PathVariable("emailId") String emailId, @RequestBody ResetPasswordDto resetPasswordDtoObject) {

		return userServiceObject.resetPassword(resetPasswordDtoObject, emailId);
	}

	@PostMapping("/userVerification/{token}")
	public ResponseEntity<String> userVerification(@PathVariable("token") String emailToken) {

		return userServiceObject.userVerification(emailToken);
	}
}