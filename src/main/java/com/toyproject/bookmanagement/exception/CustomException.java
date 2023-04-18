package com.toyproject.bookmanagement.exception;

import java.util.Map;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
	
	private Map<String, String> errorMap;
	
	public CustomException(String message) { //RuntimeException 에 message를 전달
		super(message);
	}
	public CustomException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
}
