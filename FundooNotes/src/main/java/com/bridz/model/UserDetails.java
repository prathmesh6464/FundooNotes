package com.bridz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UserDetails")
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
	private String email;
	private boolean isEmailVerified;

	@OneToMany(mappedBy = "userDetails")
	private List<NotesData> notesData = new ArrayList<NotesData>();

	@OneToMany(mappedBy = "userDetails")
	private List<LabelData> labelData = new ArrayList<LabelData>();

	// Default constructor
	public UserDetails() {

		super();
	}

	// @return the firstName
	public String getFirstName() {

		return firstName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public List<LabelData> getLabelData() {
		return labelData;
	}

	public void setLabelData(List<LabelData> labelData) {
		this.labelData = labelData;
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
	public String getEmail() {
		return email;
	}

	// @param emailId the emailId to set
	public void setEmail(String email) {
		this.email = email;
	}

	// @return the notesData
	public List<NotesData> getNotesData() {
		return notesData;
	}

	// @param notesData the notesData to set
	public void setNotesData(List<NotesData> notesData) {
		this.notesData = notesData;
	}

	// @return the isEmailVeriefied
	public boolean getIsEmailVeriefied() {
		return isEmailVerified;
	}

	// @param isEmailVeriefied the isEmailVeriefied to set
	public void setIsEmailVeriefied(boolean isEmailVeriefied) {
		this.isEmailVerified = isEmailVeriefied;
	}
}