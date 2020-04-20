package com.bridz.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ForgetPasswordDto {

	// Variables
	@NotEmpty
	@NotNull
	private String secretEmergencyWord;

	@NotEmpty
	@NotNull
	@Pattern(regexp = "[0-9]{10}")
	private String firstMobileNumber;

	// @return the secretEmergencyWord
	public String getSecretEmergencyWord() {

		return secretEmergencyWord;
	}

	// @param secretEmergencyWord the secretEmergencyWord to set
	public void setSecretEmergencyWord(String secretEmergencyWord) {

		this.secretEmergencyWord = secretEmergencyWord;
	}

	// @return the firstMobileNumber
	public String getFirstMobileNumber() {

		return firstMobileNumber;
	}

	// @param firstMobileNumber the firstMobileNumber to set
	public void setFirstMobileNumber(String firstMobileNumber) {

		this.firstMobileNumber = firstMobileNumber;
	}
}
