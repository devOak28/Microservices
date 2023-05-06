package com.user.exception;

public class ResourceNotFoundException extends Exception{

	public ResourceNotFoundException() {
		super("Resource Not Found On Server!!");
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
}
