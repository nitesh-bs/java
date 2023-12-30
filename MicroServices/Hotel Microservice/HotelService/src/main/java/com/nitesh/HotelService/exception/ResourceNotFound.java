package com.nitesh.HotelService.exception;

public class ResourceNotFound extends RuntimeException{

	public ResourceNotFound() {
		super();
	}

	public ResourceNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFound(String message) {
		super(message);
	}

	public ResourceNotFound(Throwable cause) {
		super(cause);
	}

	
}
