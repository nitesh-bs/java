package com.nitesh.springBootJwt.response;

public class OkResponse {

	private int status = 200;
	private long currentTimestamp = System.currentTimeMillis();
	private String message;
	private Object data;
	
	public OkResponse() {
	}
	public OkResponse(int status, long currentTimestamp, String message, Object data) {
		this.status = status;
		this.currentTimestamp = currentTimestamp;
		this.message = message;
		this.data = data;
	}
	
	
	public OkResponse(String message, Object data) {
		this.status = 200;
		this.currentTimestamp = System.currentTimeMillis();
		this.message = message;
		this.data = data;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Status200Response [status=" + status + ", currentTimestamp=" + currentTimestamp + ", message=" + message
				+ ", data=" + data + "]";
	}
	
	
	
}
