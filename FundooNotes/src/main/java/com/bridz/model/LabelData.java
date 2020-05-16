package com.bridz.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class LabelData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String labelName;

	@ManyToMany
	List<NotesData> notesDataEntity = new ArrayList<>();

	// Constructor
	public LabelData() {
		super();
	}

	// Constructor @param labelName @param userId
	public LabelData(String labelName) {
		super();
		this.labelName = labelName;

	}

	// @return the labelName
	public String getLabelName() {
		return labelName;
	}

	// @param labelName the labelName to set
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public List<NotesData> getNotesDataEntity() {
		return notesDataEntity;
	}

	public void setNotesDataEntity(List<NotesData> notesDataEntity) {
		this.notesDataEntity = notesDataEntity;
	}

}
