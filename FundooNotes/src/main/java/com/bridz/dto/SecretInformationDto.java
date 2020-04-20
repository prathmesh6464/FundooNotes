package com.bridz.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SecretInformationDto {

	// Variables
	@NotNull
	private String secretEmergencyWord;

	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "[0-9]*")
	private long firstMobileNumber;

	// @return the secretEmergencyWord
	public String getSecretEmergencyWord() {

		return secretEmergencyWord;
	}

	// @param secretEmergencyWord the secretEmergencyWord to set
	public void setSecretEmergencyWord(String secretEmergencyWord) {

		this.secretEmergencyWord = secretEmergencyWord;
	}

	// @return the firstMobileNumber
	public long getFirstMobileNumber() {

		return firstMobileNumber;
	}

	// @param firstMobileNumber the firstMobileNumber to set
	public void setFirstMobileNumber(long firstMobileNumber) {

		this.firstMobileNumber = firstMobileNumber;
	}
}
