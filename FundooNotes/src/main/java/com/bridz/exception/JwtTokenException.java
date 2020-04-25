package com.bridz.exception;

public class JwtTokenException extends RuntimeException {

	private static final long serialVersionUID = 632734427788097094L;

	int errorCode;

	// Constructor @param message
	public JwtTokenException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;
	}

}
