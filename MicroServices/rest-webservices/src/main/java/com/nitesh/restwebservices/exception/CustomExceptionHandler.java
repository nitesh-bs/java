package com.nitesh.restwebservices.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllException(Exception exception, WebRequest request) {

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				errors.toString(), System.currentTimeMillis());
		return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(BadCredential exception) {
		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

	/*
	 * @ExceptionHandler public ResponseEntity<ErrorResponse>
	 * handleException(UserNotFoundException exe){
	 * 
	 * ErrorResponse errorResponse = new ErrorResponse();
	 * errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
	 * errorResponse.setMessage(exe.getMessage());
	 * errorResponse.setTimeStamp(System.currentTimeMillis());
	 * 
	 * return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND); }
	 * 
	 * @ExceptionHandler public ResponseEntity<ErrorResponse>
	 * handleException(Exception exe){
	 * 
	 * ErrorResponse errorResponse = new ErrorResponse();
	 * errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
	 * errorResponse.setMessage(exe.getMessage());
	 * errorResponse.setTimeStamp(System.currentTimeMillis());
	 * 
	 * return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST); }
	 */
}
