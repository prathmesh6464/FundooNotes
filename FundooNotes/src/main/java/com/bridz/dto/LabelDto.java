package com.bridz.dto;

public class LabelDto {

	private String labelName;
	private long userId;

	// Constructor
	public LabelDto() {
		super();
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
