package com.bridz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private long mobileNumber;
	private String secretEmergencyWord;
	private long firstMobileNumber;
	
	@OneToMany(mappedBy = "userDetails")
	private List<NotesData> notesData = new ArrayList<>();
	
	// Constructor
	public UserDetails(String firstName, String lastName, String userName, String password, long mobileNumber,
			String secretEmergencyWord, long firstMobileNumber, List<NotesData> notesData) {

		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.secretEmergencyWord = secretEmergencyWord;
		this.firstMobileNumber = firstMobileNumber;
		this.notesData = notesData;
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

	// @return the notesData
	public List<NotesData> getNotes() {

		return notesData;
	}

	// @param notesData the notesData to set
	public void setNotes(List<NotesData> notesData) {

		this.notesData = notesData;
	}
}
