package com.bridz.exception;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = -5822551539758568833L;

	int errorCode;

	// Constructor @param message
	public UserException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;
	}

}
