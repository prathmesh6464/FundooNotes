package com.bridz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NotesData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String message;

	@ManyToOne
	private UserDetails userDetails;

	// Constructor
	public NotesData(String message, UserDetails userDetails) {

		super();
		this.message = message;
		this.userDetails = userDetails;
	}

	// @return the noteId
	public long getNoteId() {

		return id;
	}

	// @param noteId the noteId to set
	public void setNoteId(long noteId) {

		this.id = noteId;
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

