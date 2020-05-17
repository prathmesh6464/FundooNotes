package com.bridz.controller;

import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginDto userLoginDto) {

		return userService.login(userLoginDto);
	}

	@PostMapping("/registration")
	public ResponseEntity<String> registration(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {

		return userService.registerUser(userRegistrationDto);
	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<String> forgetPassword(@Valid @RequestBody ForgetPasswordDto forgetPasswordDto) {

		return userService.forgetPassword(forgetPasswordDto);
	}

	@PutMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto) {

		return userService.resetPassword(resetPasswordDto);
	}

	@GetMapping("/verification/{token}")
	public ResponseEntity<String> verification(@PathVariable("token") String emailToken) {

		return userService.verification(emailToken);
	}
}