package com.bridz.service;

import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import org.springframework.http.ResponseEntity;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.UserRegistrationDto;

public interface UserService {

	public ResponseEntity<String> registerUser(UserRegistrationDto userRegisterDtoObject);

	public ResponseEntity<String> userLogin(LoginDto userLoginDtoObject);

	public ResponseEntity<String> forgetPassword(ForgetPasswordDto forgetPasswordDtoObject);

	public ResponseEntity<String> resetPassword(ResetPasswordDto resetPasswordDtoObject, String emailId);

	public ResponseEntity<String> userVerification(String emailToken);

}
