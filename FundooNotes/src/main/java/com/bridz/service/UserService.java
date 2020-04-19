package com.bridz.service;

import java.util.List;

import com.bridz.dto.LoginDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.SecretInformationDto;
import com.bridz.dto.UserDetailsDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.model.UserDetails;
import com.bridz.response.Response;

public interface UserService {

	public Response registerUser(UserRegistrationDto userRegisterDtoObject);

	public List<UserDetailsDto> userLogin(LoginDto userLoginDtoObject);

	public Response forgetPassword(SecretInformationDto secretInformationDataDtoObject);

	public Response resetPassword(ResetPasswordDto resetPassword, String secretWordDtoObject);

}
