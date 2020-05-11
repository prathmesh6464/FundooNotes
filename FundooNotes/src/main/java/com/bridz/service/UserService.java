package com.bridz.service;

import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import org.springframework.http.ResponseEntity;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.UserRegistrationDto;

public interface UserService {

	public ResponseEntity<String> registerUser(UserRegistrationDto userRegisterDto);

	public ResponseEntity<String> login(LoginDto userLoginDto);

	public ResponseEntity<String> forgetPassword(ForgetPasswordDto forgetPasswordDto);

	public ResponseEntity<String> resetPassword(ResetPasswordDto resetPasswordDto);

	public ResponseEntity<String> verification(String emailToken);

}
