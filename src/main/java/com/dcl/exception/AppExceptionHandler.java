package com.dcl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(exception = AppException.class)
	public ResponseEntity<?> handleexception(AppException exception){
		return new ResponseEntity<>(exception.getMessage(),exception.gethttpstatus());
	}
	
	
	@ExceptionHandler(exception=Exception.class)
	public ResponseEntity<?> handler(){
		Exception exception=new Exception();
		return new ResponseEntity ("Something Went Wrong!",HttpStatus.OK);
	}
}
