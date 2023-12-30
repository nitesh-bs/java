package com.rental.stayIn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
// extends ResponseEntityExceptionHandler for field error validation exception
public class CustomExceptionHandler  {


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exception,WebRequest request){
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage(),System.currentTimeMillis(),null);
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BadCredential.class)
	public final ResponseEntity<SuccessResponse> handleUserNotFoundException(Exception exception,WebRequest request){
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(),System.currentTimeMillis(),null);
		return new ResponseEntity<SuccessResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(
//			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getFieldError().getDefaultMessage(),System.currentTimeMillis());
//		return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
//		
//	}
	
	
}

