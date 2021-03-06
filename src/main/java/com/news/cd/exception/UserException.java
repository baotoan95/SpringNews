package com.news.cd.exception;

@SuppressWarnings("serial")
public class UserException extends Exception {
	private String message;
	
	public UserException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public void printStackTrace() {
		System.out.println(message);
	}
}
