package com.nitesh.UserService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nitesh.UserService.payload.ApiResponse;

@RestControllerAdvice
public class GlobelExceptionHandler {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFound resourceNotFound){
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage(resourceNotFound.getMessage());
		apiResponse.setSuccess(true);
		apiResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
}
