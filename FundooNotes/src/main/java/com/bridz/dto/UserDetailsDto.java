package com.bridz.dto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

public class UserDetailsDto {

	// Variables
	private String firstName;
	private String lastName;
	private String userName;
	
	@OneToMany(mappedBy = "userDetails")
	private List<NotesDto> notesDataDto = new ArrayList<>();

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

	// @return the notesDataDto
	public List<NotesDto> getNotes() {

		return notesDataDto;
	}

	// @param notesDataDto the notesDataDto to set
	public void setNotes(List<NotesDto> notesDataDto) {

		this.notesDataDto = notesDataDto;
	}
}
