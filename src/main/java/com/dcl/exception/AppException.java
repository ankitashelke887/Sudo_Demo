package com.dcl.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{

	private HttpStatus httpstatus;
	
	public AppException(String message, HttpStatus httpstatus) {
		super(message);
		this.httpstatus=httpstatus;
	}
	
	public HttpStatus gethttpstatus() {
		return httpstatus;
	}
}
