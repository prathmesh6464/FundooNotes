package com.bridz.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
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
	UserDetails userDetailEntity;
	
	@OneToMany(mappedBy = "notesDataEntity")
	public List<LabelData> labelDataEnitiy;

	// Default constructor
	public NotesData() {
		super();
	}

	// @return the description
	public String getDescription() {
		return description;
	}

	// Constructor
	public NotesData(String title, String description, boolean isTrash, boolean isArchive, boolean isPined,
			String reminderDateTime) {
		super();
		this.title = title;
		this.description = description;
		this.isTrash = isTrash;
		this.isArchive = isArchive;
		this.isPined = isPined;
		this.reminderDateTime = reminderDateTime;
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
		return userDetailEntity;
	}

	public void setUserDetailEntity(UserDetails userDetailEntity) {
		this.userDetailEntity = userDetailEntity;
	}

	public List<LabelData> getLabelDataEnitiy() {
		return labelDataEnitiy;
	}

	public void setLabelDataEnitiy(List<LabelData> labelDataEnitiy) {
		this.labelDataEnitiy = labelDataEnitiy;
	}

}
