package com.bridz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDetails {

	// Variables
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String mobileNumber;
	private String emailId;

	// Default constructor
	public UserDetails() {

		super();
	}

	// Parameterized constructor
	public UserDetails(String firstName, String lastName, String userName, String password, String mobileNumber,
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

	// @return the emailId
	public String getEmailId() {
		return emailId;
	}

	// @param emailId the emailId to set
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
