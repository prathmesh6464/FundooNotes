package com.bridz.service;

import java.util.Map;
import com.bridz.dto.ForgetPasswordDto;

public interface JwtService {
	
	public String generateToken(ForgetPasswordDto forgetPasswordDtoObject);
	public String createToken(Map<String, Object> claims, String subject);

}
