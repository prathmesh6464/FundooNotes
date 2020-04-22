package com.bridz.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class UserRegistrationDto {

	// Variables
	@NotEmpty
	@NotNull
	@Pattern(regexp = "[^0-9]*")
	private String firstName;

	@NotEmpty
	@NotNull
	@Pattern(regexp = "[^0-9]*")
	private String lastName;

	@NotEmpty
	@NotNull
	private String userName;

	@NotEmpty
	@NotNull
	@Size(min = 8)
	private String password;

	@NotEmpty
	@NotNull
	@Pattern(regexp = "([0-9]{10})")
	private String mobileNumber;

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

	// Default constructor
	public UserRegistrationDto() {

		super();
	}

	// Parameterized constructor
	public UserRegistrationDto(String firstName, String lastName, String userName, String password, String mobileNumber,
			String emailId) {

		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
	}

	// @return the firstName
	public String getFirstName() {

		return firstName;
	}

	// @param firstName the firstName to set
	public void setFirstName(String firstName) {

		this.firstName = firstName;
	}

	// @return the lastName
	public String getLastName() {

		return lastName;
	}

	// @param lastName the lastName to set
	public void setLastName(String lastName) {

		this.lastName = lastName;
	}

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

	// @return the mobileNumber
	public String getMobileNumber() {

		return mobileNumber;
	}

	// @param mobileNumber the mobileNumber to set
	public void setMobileNumber(String mobileNumber) {

		this.mobileNumber = mobileNumber;
	}
}
