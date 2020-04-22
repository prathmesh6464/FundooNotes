package com.bridz.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ForgetPasswordDto {

	// Variables
	@NotEmpty
	@NotNull
	private String emailId;

	// @return the emailId
	public String getEmailId() {
		return emailId;
	}

	// @param emailId the emailId to set
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
