package com.bridz.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "labelData")
@JsonIgnoreProperties({ "userDetails" })
public class LabelData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String labelName;

	@ManyToMany
	@JoinTable(name = "noteLabel", joinColumns = @JoinColumn(referencedColumnName = "id"))
	private List<NotesData> notesData = new ArrayList<NotesData>();

	@ManyToOne
	@JoinColumn(name = "userDetails_id", nullable = false)
	private UserDetails userDetails;

	// Constructor
	public LabelData() {
		super();
	}

	// @return the labelName
	public String getLabelName() {
		return labelName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<NotesData> getNotesData() {
		return notesData;
	}

	public void setNotesData(List<NotesData> notesData) {
		this.notesData = notesData;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	// @param labelName the labelName to set
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public List<NotesData> getNotesDataEntity() {
		return notesData;
	}

	public void setNotesDataEntity(List<NotesData> notesDataEntity) {
		this.notesData = notesDataEntity;
	}

}