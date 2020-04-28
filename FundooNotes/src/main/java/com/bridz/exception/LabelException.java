package com.bridz.exception;

public class LabelException extends RuntimeException {

	private static final long serialVersionUID = 6326975488405199013L;
	
	int errorCode;

	// Constructor @param message
	public LabelException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;
	}

}
