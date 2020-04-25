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
	private String title;
	private String description;

	@ManyToOne
	public UserDetails userDetails;

	// Default constructor
	public NotesData() {
		super();
	}

	// Constructor
	public NotesData(String title, String description, UserDetails userDetails) {

		super();
		this.title = title;
		this.description = description;
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

	// @return the userDetails
	public UserDetails getUserDetails() {
		return userDetails;
	}

	// @param userDetails the userDetails to set
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	// @return the description
	public String getDescription() {
		return description;
	}

	// @param description the description to set
	public void setDescription(String description) {
		this.description = description;
	}

	// @return the title
	public String getTitle() {
		return title;
	}

	// @param title the title to set
	public void setTitle(String title) {
		this.title = title;
	}
}
