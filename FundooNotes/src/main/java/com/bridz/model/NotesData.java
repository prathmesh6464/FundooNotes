package com.bridz.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "notes")
@JsonIgnoreProperties({ "userDetails", "labelData" })
public class NotesData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String description;
	private boolean isTrash;
	private boolean isArchive;
	private boolean isPined;
	private String reminderDateTime;
	
	
	@ManyToOne
	@JoinColumn(name = "userDetails_id", nullable = false)
	private UserDetails userDetails;
	
	@ManyToMany
	private List<LabelData> labelData = new ArrayList<LabelData>();
	

	// Default constructor
	public NotesData() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<LabelData> getLabelData() {
		return labelData;
	}

	public void setLabelData(List<LabelData> labelData) {
		this.labelData = labelData;
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

	// @return the isTrash
	public boolean isTrash() {
		return isTrash;
	}

	// @param isTrash the isTrash to set
	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	// @return the isArchive
	public boolean isArchive() {
		return isArchive;
	}

	// @param isArchive the isArchive to set
	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	// @return the isPined
	public boolean isPined() {
		return isPined;
	}

	// @param isPined the isPined to set
	public void setPined(boolean isPined) {
		this.isPined = isPined;
	}

	// @return the reminderDateTime
	public String getReminderDateTime() {
		return reminderDateTime;
	}

	// @param reminderDateTime the reminderDateTime to set
	public void setReminderDateTime(String reminderDateTime) {
		this.reminderDateTime = reminderDateTime;
	}

	public UserDetails getUserDetailEntity() {
		return userDetails;
	}

	public void setUserDetailEntity(UserDetails userDetailsEntity) {
		this.userDetails = userDetailsEntity;
	}

	public List<LabelData> getLabelDataEnitiy() {
		return labelData;
	}

	public void setLabelDataEnitiy(List<LabelData> labelDataEnitiy) {
		this.labelData = labelDataEnitiy;
	}

}