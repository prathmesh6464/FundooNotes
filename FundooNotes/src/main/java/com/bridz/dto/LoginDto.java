package com.bridz.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginDto {

	// Variables
	@NotEmpty
	@NotNull
	@Pattern(regexp = "[^0-9]*")
	private String userName;

	@NotEmpty
	@NotNull
	@Size(min = 8)
	private String password;

	// @return the userName
	public String getUserName() {

		return userName;
	}

	// @param userName the userName to set
	public void setUserName(String userName) {

		this.userName = userName;
	}

	// @return the password
	public String getPassword() {

		return password;
	}

	// @param password the password to set
	public void setPassword(String password) {

		this.password = password;
	}
}
