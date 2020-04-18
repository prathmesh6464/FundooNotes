package com.bridz.service;

import java.util.List;
import com.bridz.model.LoginData;
import com.bridz.model.ResetPasswordData;
import com.bridz.model.SecretInformation;
import com.bridz.model.UserDetails;
import com.bridz.response.Response;

public interface UserService {
	
	public Response registerUser(UserDetails userDetails);
	public List<UserDetails> userLogin(LoginData  userLoginDtoObject);
	public Response forgetPassword(SecretInformation secretInformationData);
	public Response resetPassword(ResetPasswordData resetPasswordDto, String secretWord);
	
}
