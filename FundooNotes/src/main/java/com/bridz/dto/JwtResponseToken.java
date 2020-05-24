package com.bridz.dto;

public class JwtResponseToken {

	String jwtToken;

	public JwtResponseToken(String jwtToken) {

		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public String setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
		return this.jwtToken;
	}

}
