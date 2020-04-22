package com.bridz.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.bridz.dto.ForgetPasswordDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImplementation implements JwtService {

	String SECRET_KEY = "bridzSecret";

	@Override
	public String generateToken(ForgetPasswordDto forgetPasswordDtoObject) {

		Map<String, Object> claims = new HashMap<>();

		return createToken(claims, forgetPasswordDtoObject.getEmailId());
	}

	@Override
	public String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 5))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

}
