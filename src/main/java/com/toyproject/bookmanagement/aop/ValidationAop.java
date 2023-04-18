package com.toyproject.bookmanagement.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.toyproject.bookmanagement.exception.CustomException;


@Component
@Aspect
public class ValidationAop {
	
	@Pointcut("@annotation(com.toyproject.bookmanagement.aop.annotation.ValidAspect)")
	private void pointCut() {
		
	}
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable { //joinPoint (dto, BindingResult) 들어있음
		
		Object[] args = joinPoint.getArgs();
		BindingResult bindingResult = null;
		
		for(Object arg : args) {
			if(arg.getClass() == BeanPropertyBindingResult.class) {
				bindingResult = (BeanPropertyBindingResult) arg;
			}
		}
		
		if(bindingResult.hasErrors()) { //오류가 있는지 없는지 확인
			Map<String, String> errorMap = new HashMap<>();
			
			bindingResult.getFieldErrors().forEach(error -> {
				errorMap.put(error.getField(), error.getDefaultMessage());
			});
			throw new CustomException("Validation Failed", errorMap);
		}
		
		return joinPoint.proceed();
	}
}
