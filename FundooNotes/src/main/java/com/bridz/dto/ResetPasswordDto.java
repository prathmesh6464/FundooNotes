package com.bridz.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResetPasswordDto {

	// Variable
	@NotNull
	@Size(min = 8)
	private String password;

	@NotNull
	@Size(min = 8)
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
