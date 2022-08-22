package com.dev.rest.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value={ApiRequestException.class})
	public ResponseEntity<Object> HandleApiExcpetion(ApiRequestException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		DuplicateIdException duplicateIdException = new DuplicateIdException(e.getMessage(), e, badRequest,
				ZonedDateTime.now());
		return new ResponseEntity<>(duplicateIdException, badRequest); 
		
	}

}
