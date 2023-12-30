package com.nitesh.springBootJwt.response;

public class ErrorResponse {
	private int status;
	private long currentTimestamp;
	private String message;
	
	public ErrorResponse() {
		
	}

	public ErrorResponse(int status, long currentTimestamp, String message) {
		this.status = status;
		this.currentTimestamp = currentTimestamp;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCurrentTimestamp() {
		return currentTimestamp;
	}

	public void setCurrentTimestamp(long currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", currentTimestamp=" + currentTimestamp + ", message=" + message
				+ "]";
	}
	
	
}
