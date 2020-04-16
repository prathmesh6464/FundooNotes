package com.bridz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long noteId;
	private String message;
	
	@ManyToOne
	private UserDetails userDetails;

	// @return the noteId
	public long getNoteId() {

		return noteId;
	}

	// @param noteId the noteId to set
	public void setNoteId(long noteId) {

		this.noteId = noteId;
	}

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
