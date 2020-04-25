package com.bridz.dto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.bridz.model.UserDetails;

public class NotesDto {

	@NotEmpty
	@NotNull
	@Size(min = 3)
	private String title;

	@NotEmpty
	@NotNull
	@Size(min = 5)
	private String description;

	@ManyToOne
	private UserDetails userDetails;

	// @return the title
	public String getTitle() {
		return title;
	}

	// @param title the title to set
	public void setTitle(String title) {
		this.title = title;
	}

	// @return the description
	public String getDescription() {
		return description;
	}

	// @param description the description to set
	public void setDescription(String description) {
		this.description = description;
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
