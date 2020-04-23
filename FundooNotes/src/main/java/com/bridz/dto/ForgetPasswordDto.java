package com.bridz.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ForgetPasswordDto {

	// Variables
	@NotEmpty
	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9]*@[a-zA-Z]+[.][a-zA-Z]+")
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
