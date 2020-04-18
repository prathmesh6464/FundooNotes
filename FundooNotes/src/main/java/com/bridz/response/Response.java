package com.bridz.response;

public class Response {

	//Variable
	private int responseStatus;
	private String responseMessage;

	// Constructor
	public Response(String responseMessage, int responseStatus) {
		super();
		this.responseStatus = responseStatus;
		this.responseMessage = responseMessage;
	}

	// @return the responseStatus
	public int getResponseStatus() {

		return responseStatus;
	}

	// @param responseStatus the responseStatus to set
	public void setResponseStatus(int responseStatus) { 

		this.responseStatus = responseStatus;
	}

	// @return the responseMessage
	public String getResponseMessage() {

		return responseMessage;
	}

	// @param responseMessage the responseMessage to set
	public void setResponseMessage(String responseMessage) {

		this.responseMessage = responseMessage;
	}
}
