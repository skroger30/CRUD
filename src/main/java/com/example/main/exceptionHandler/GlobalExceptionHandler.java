package com.example.main.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice // Used to handle specific as well as global exceptions in single class
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MobileNumberExistsException.class)
	public ResponseEntity<ErrorDetails>  mobileNoExistsException(MobileNumberExistsException exception, WebRequest webRequest) {
		 
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"MOBILE_ALREADY_EXISTS");		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
//	Exception is super class of all checked and un-checked exceptions. 
//	In case of exceptions apart from specific and controller level exeptions ocuur, following method will eb called.
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails>  handleGlobalException(Exception exception, WebRequest webRequest) {
		 
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"INTERNAL_SERVER_ERROR");		
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
