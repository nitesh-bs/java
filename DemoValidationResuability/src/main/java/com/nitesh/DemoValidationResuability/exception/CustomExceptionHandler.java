package com.nitesh.DemoValidationResuability.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler  {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
		
		return new ResponseEntity<>(exception.getMessage()+"----", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
