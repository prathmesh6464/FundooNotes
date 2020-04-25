package com.bridz.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Service;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.UserRegistrationDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtToken {

	String SECRET_KEY = "bridzSecret";

	// Generating token for forget password
	public String generateToken(ForgetPasswordDto forgetPasswordDtoObject) {

		Map<String, Object> claims = new HashMap<>();

		return createToken(claims, forgetPasswordDtoObject.getEmailId());
	}

	// Generating token for user verification
	public String generateToken(UserRegistrationDto userRegistrationDto) {

		Map<String, Object> claims = new HashMap<>();

		return createToken(claims, userRegistrationDto.getEmailId());
	}

	// Create token method
	public String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 5))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

}