package com.example.main.exceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // Used to handle specific as well as global exceptions in single class
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
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
	
//	Custom Validation Response
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		Map<String, String> errors = new HashMap<>();
		 List<ObjectError> errorList= ex.getBindingResult().getAllErrors();
		 errorList.forEach((error) -> {
			 String fieldName  = ((FieldError) error).getField();
			 String msg = error.getDefaultMessage();
			 errors.put(fieldName, msg);		 
		 });
		 
		 return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}
