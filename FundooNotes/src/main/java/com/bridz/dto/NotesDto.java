package com.bridz.dto;

import javax.persistence.ManyToOne;
import com.bridz.model.UserDetails;

public class NotesDto {

	private String message;

	@ManyToOne
	private UserDetails userDetails;

	// @return the message
	public String getMessage() {

		return message;
	}

	// @param message the message to set
	public void setMessage(String message) {

		this.message = message;
	}

	// @return the userDetails
	public UserDetails getUserDetails() {

		return userDetails;
	}

	// @param userDetails the userDetails to set
	public void setUserDetails(UserDetails userDetails) {

		this.userDetails = userDetails;
	}

}
