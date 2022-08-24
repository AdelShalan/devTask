package com.dev.rest.exception;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {
	private HttpStatus badRequest = HttpStatus.BAD_REQUEST;

	@ExceptionHandler(value = { ApiRequestException.class })
	public ResponseEntity<Object> HandleApiExcpetion(ApiRequestException e) {
		CustomException duplicateIdException = new CustomException(e.getMessage(), e, badRequest, ZonedDateTime.now());
		return new ResponseEntity<>(duplicateIdException, badRequest);

	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleException(Exception e) {
		CustomException emptyFieldException = new CustomException(e.getMessage(), e, badRequest,
				ZonedDateTime.now());
		return new ResponseEntity<>(emptyFieldException, badRequest);
	}

}
