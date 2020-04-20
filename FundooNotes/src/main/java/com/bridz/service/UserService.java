package com.bridz.service;

import java.util.List;

import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.UserDetailsDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.model.UserDetails;
import com.bridz.response.Response;

public interface UserService {

	public Response registerUser(UserRegistrationDto userRegisterDtoObject);

	public List<UserDetailsDto> userLogin(LoginDto userLoginDtoObject);

	public Response forgetPassword(ForgetPasswordDto forgetPasswordDtoObject);

	public Response resetPassword(ResetPasswordDto resetPassword, String secretWordDtoObject);

}
