package com.bridz.service;

import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import org.springframework.http.ResponseEntity;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.response.Response;

public interface UserService {

	public Response registerUser(UserRegistrationDto userRegisterDtoObject);

	public Response userLogin(LoginDto userLoginDtoObject);

	public ResponseEntity<String> forgetPassword(ForgetPasswordDto forgetPasswordDtoObject);

	public Response resetPassword(ResetPasswordDto resetPasswordDtoObject);

}
