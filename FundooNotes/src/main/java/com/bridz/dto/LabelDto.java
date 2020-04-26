package com.bridz.dto;

public class LabelDto {

	private String lableMessage;

	// Constructor
	public LabelDto() {
		super();
	}

	// Constructor @param lableMessage
	public LabelDto(String lableMessage) {
		super();
		this.lableMessage = lableMessage;
	}

	// @return the lableMessage
	public String getLableMessage() {
		return lableMessage;
	}

	// @param lableMessage the lableMessage to set
	public void setLableMessage(String lableMessage) {
		this.lableMessage = lableMessage;
	}
}
