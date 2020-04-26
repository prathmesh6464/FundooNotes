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
	
	private String LabelMessage;

	// Constructor
	public LabelData() {
		super();
	}

	// Constructor @param id @param labelMessage
	public LabelData(String labelMessage) {
		super();
		LabelMessage = labelMessage;
	}

	// @return the labelMessage
	public String getLabelMessage() {
		return LabelMessage;
	}

	// @param labelMessage the labelMessage to set
	public void setLabelMessage(String labelMessage) {
		LabelMessage = labelMessage;
	}

}
