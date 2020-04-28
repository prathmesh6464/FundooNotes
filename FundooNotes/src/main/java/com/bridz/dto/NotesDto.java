package com.bridz.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NotesDto {

	@NotEmpty
	@NotNull
	@Size(min = 3)
	private String title;

	@NotEmpty
	@NotNull
	@Size(min = 5)
	private String description;

	private boolean isTrash;
	private boolean isArchive;
	private boolean isPined;
	private String reminderDateTime;

	// @return the title
	public String getTitle() {
		return title;
	}

	// @param title the title to set
	public void setTitle(String title) {
		this.title = title;
	}

	// @return the description
	public String getDescription() {
		return description;
	}

	// @param description the description to set
	public void setDescription(String description) {
		this.description = description;
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

}
