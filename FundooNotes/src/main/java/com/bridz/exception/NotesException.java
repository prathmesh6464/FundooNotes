package com.bridz.exception;

public class NotesException extends RuntimeException {

	private static final long serialVersionUID = -4005000293040820290L;

	int errorCode;

	// Constructor @param message
	public NotesException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;
	}

}
