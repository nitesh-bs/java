package com.nitesh.stayIn.exception;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
// extends ResponseEntityExceptionHandler for field error validation exception
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exception.getMessage(), System.currentTimeMillis(), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<Object> handleAccessDeniedException(Exception exception, WebRequest request) {
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.UNAUTHORIZED.value(), exception.getMessage(),
				System.currentTimeMillis(), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<SuccessResponse> handleBadRequestException(Exception exception) {
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				System.currentTimeMillis(), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<SuccessResponse> handleUserNotFoundException(Exception exception) {
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				System.currentTimeMillis(), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
				ex.getFieldError().getDefaultMessage(), System.currentTimeMillis(), null);
		return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " -> parameter is missing in request";

		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(),
				System.currentTimeMillis(), null);
		return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);

		// If you want to throw apiError directly, uncomment this
		// return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(),
				System.currentTimeMillis(), null);
		return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getSimpleName();

		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.BAD_REQUEST.value(), error,
				System.currentTimeMillis(), null);
		return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		// If you want to throw apiError directly, uncomment this
		// return new ResponseEntity<Object>(apiError, new HttpHeaders(),
		// apiError.getStatus());
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException exc) {

		List<String> details = new ArrayList<>();
		details.add(exc.getMessage());
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.NOT_FOUND.value(), details.toString(),
				System.currentTimeMillis(), null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {

		List<String> details = new ArrayList<>();
		details.add(exc.getMessage());
		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.EXPECTATION_FAILED.value(), details.toString(),
				System.currentTimeMillis(), null);
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errorResponse);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleMaxSizeException(IllegalArgumentException exc) {

		SuccessResponse errorResponse = new SuccessResponse(HttpStatus.BAD_GATEWAY.value(),
				"Invalid " + exc.getMessage(), System.currentTimeMillis(), null);
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
	}
}
