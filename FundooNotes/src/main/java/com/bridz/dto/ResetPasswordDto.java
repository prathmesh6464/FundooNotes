package com.bridz.dto;

public class ResetPasswordDto {

	//Variables
	private String password;
	private String confirmPassword;

	// @return the password
	public String getPassword() {

		return password;
	}

	// @param password the password to set
	public void setPassword(String password) {

		this.password = password;
	}

	// @return the confirmPassword
	public String getConfirmPassword() {

		return confirmPassword;
	}

	// @param confirmPassword the confirmPassword to set
	public void setConfirmPassword(String confirmPassword) {

		this.confirmPassword = confirmPassword;
	}
}