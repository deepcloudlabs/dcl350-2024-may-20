package com.example.hr.dto.error;

public class ErrorResponse {
	private String message;
	private String source;

	public ErrorResponse() {
	}

	public ErrorResponse(String message, String source) {
		this.message = message;
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
