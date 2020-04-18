package com.bridz.dto;

public class SecretInformationDto {

	// Variables
	private String secretEmergencyWord;
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
