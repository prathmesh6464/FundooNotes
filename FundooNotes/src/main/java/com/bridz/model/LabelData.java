package com.bridz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LabelData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String labelName;
	private long userId;

	// Constructor
	public LabelData() {
		super();
	}

	// Constructor @param labelName @param userId
	public LabelData(String labelName, long userId) {
		super();
		this.labelName = labelName;
		this.userId = userId;
	}

	// @return the labelName
	public String getLabelName() {
		return labelName;
	}

	// @param labelName the labelName to set
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	// @return the userId
	public long getUserId() {
		return userId;
	}

	// @param userId the userId to set
	public void setUserId(long userId) {
		this.userId = userId;
	}

}
