package com.toyproject.bookmanagement.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.toyproject.bookmanagement.dto.common.ErrorResponseDto;
import com.toyproject.bookmanagement.exception.CustomException;

@RestControllerAdvice
public class AdviceController { 
	
	@ExceptionHandler(CustomException.class) //CustomException예외가 발생하면 실행된다
	public ResponseEntity<?> customException(CustomException e) { // throw new CustomException("Validation Failed", errorMap); => e 
		
		return ResponseEntity.badRequest().body(new ErrorResponseDto<>(e.getMessage(), e.getErrorMap()));
	}
}
