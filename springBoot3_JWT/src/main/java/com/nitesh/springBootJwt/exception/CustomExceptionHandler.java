package com.nitesh.springBootJwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nitesh.springBootJwt.response.ErrorResponse;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllException(Exception exception,WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),System.currentTimeMillis(),exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception exception,WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),System.currentTimeMillis(),exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
	public final ResponseEntity<Object> handleAccessDeniedException(Exception exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), System.currentTimeMillis(),
				exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

}
