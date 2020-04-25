package com.bridz.exception;

public class JmsException extends RuntimeException {

	private static final long serialVersionUID = 3691397454430391187L;

	int errorCode;

	// Constructor @param message
	public JmsException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;

	}
	
}
