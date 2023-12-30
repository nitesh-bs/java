package com.nitesh.springdemo.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException studentNotFoundException){
		
		StudentErrorResponse errorResponse = new StudentErrorResponse();
		
		errorResponse.setStauts(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(studentNotFoundException.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exception){
	
		StudentErrorResponse errorResponse = new StudentErrorResponse();
		errorResponse.setStauts(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	
		
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(BadCredential exception){
	StudentErrorResponse errorResponse = new StudentErrorResponse();
		
		errorResponse.setStauts(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	
		
	}
}
