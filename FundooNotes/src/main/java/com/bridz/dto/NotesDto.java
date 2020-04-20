package com.bridz.dto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.bridz.model.UserDetails;

public class NotesDto {

	@NotEmpty
	@NotNull
	@Size(min = 5)
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
