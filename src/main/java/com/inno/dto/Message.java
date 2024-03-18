package com.inno.dto;

import java.util.Date;

public class Message {

	private String name;
	private Integer type;
	private Date receivedTime;
	private String messageText;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Message(String name, Integer type, Date receivedTime, String messageText) {
		super();
		this.name = name;
		this.type = type;
		this.receivedTime = receivedTime;
		this.messageText = messageText;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
}
