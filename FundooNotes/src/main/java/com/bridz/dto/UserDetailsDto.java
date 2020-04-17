package com.bridz.dto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import com.bridz.model.NotesData;

public class UserDetailsDto {

	// Variables
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private long mobileNumber;
	private String secretEmergencyWord;
	private long firstMobileNumber;

	@OneToMany(mappedBy = "userDetails")
	private List<NotesDataDto> notesDataDto = new ArrayList<>();

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
	public long getMobileNumber() {

		return mobileNumber;
	}

	// @param mobileNumber the mobileNumber to set
	public void setMobileNumber(long mobileNumber) {

		this.mobileNumber = mobileNumber;
	}

	// @return the firstMobileNumber
	public long getFirstMobileNumber() {

		return firstMobileNumber;
	}

	// @param firstMobileNumber the firstMobileNumber to set
	public void setFirstMobileNumber(long firstMobileNumber) {

		this.firstMobileNumber = firstMobileNumber;
	}

	// @return the secretEmergencyWord

	public String getSecretEmergencyWord() {

		return secretEmergencyWord;
	}

	// @param secretEmergencyWord the secretEmergencyWord to set
	public void setSecretEmergencyWord(String secretEmergencyWord) {

		this.secretEmergencyWord = secretEmergencyWord;
	}

	// @return the notesDataDto
	public List<NotesDataDto> getNotes() {

		return notesDataDto;
	}

	// @param notesDataDto the notesDataDto to set
	public void setNotes(List<NotesDataDto> notesDataDto) {

		this.notesDataDto = notesDataDto;
	}
}
