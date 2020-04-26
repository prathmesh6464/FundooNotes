package com.bridz.dto;

public class LabelDto {

	private String lableName;
	private long userId;

	// Constructor
	public LabelDto() {
		super();
	}

	// Constructor @param lableName @param userId
	public LabelDto(String lableName, long userId) {
		super();
		this.lableName = lableName;
		this.setUserId(userId);
	}

	// @return the lableName
	public String getLableName() {
		return lableName;
	}

	//@param lableName the lableName to set	 
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	// return the userId	 
	public long getUserId() {
		return userId;
	}

	//@param userId the userId to set
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
