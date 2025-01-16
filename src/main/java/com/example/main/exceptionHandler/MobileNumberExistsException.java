package com.example.main.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MobileNumberExistsException extends RuntimeException{

	private String msg;
	public MobileNumberExistsException(String msg) {
		super(msg);
	}
}
