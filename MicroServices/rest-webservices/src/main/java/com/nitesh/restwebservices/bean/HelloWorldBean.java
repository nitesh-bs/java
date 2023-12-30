package com.nitesh.restwebservices.bean;

public class HelloWorldBean {
	
	private String messages;

	public HelloWorldBean(String messages) {
		this.messages = messages;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [messages=" + messages + "]";
	}
	
	

}
