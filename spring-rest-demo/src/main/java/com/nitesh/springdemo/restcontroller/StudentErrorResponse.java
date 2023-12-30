package com.nitesh.springdemo.restcontroller;

public class StudentErrorResponse {

	private int stauts;
	private String message;
	private long timeStamp;
	
	public StudentErrorResponse() {
		
	}

	public StudentErrorResponse(int stauts, String message, long timeStamp) {
		super();
		this.stauts = stauts;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStauts() {
		return stauts;
	}

	public void setStauts(int stauts) {
		this.stauts = stauts;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
