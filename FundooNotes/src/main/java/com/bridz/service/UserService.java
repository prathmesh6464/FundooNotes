package com.bridz.service;

import java.util.List;
import com.bridz.dto.LoginDataDto;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.SecretInformationDto;
import com.bridz.model.UserDetails;
import com.bridz.response.Response;

public interface UserService {
	
	public Response registerUser(UserDetails userDetails);
	public List<UserDetails> userLogin(LoginDataDto  userLoginDtoObject);
	public Response forgetPassword(SecretInformationDto secretInformationData);
	public Response resetPassword(ResetPasswordDto resetPasswordDto, String secretWord);
	
}
