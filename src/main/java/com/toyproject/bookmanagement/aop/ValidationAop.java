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
		
		Object[] args = joinPoint.getArgs(); // [0]Dto , [1]bindingresult가 들어있음
		BindingResult bindingResult = null;
		
		for(Object arg : args) {//1번째는 dto 2번째는 bindingResult 
			if(arg.getClass() == BeanPropertyBindingResult.class) { // bindingResult == BeanPropertyBindingResult 같으므로 실행됨
				bindingResult = (BeanPropertyBindingResult) arg;  //다운캐스팅
			}
		}
		
		if(bindingResult.hasErrors()) { //오류가 있는지 없는지 확인
			Map<String, String> errorMap = new HashMap<>();
			
			bindingResult.getFieldErrors().forEach(error -> {
				errorMap.put(error.getField(), error.getDefaultMessage()); //key : value (오류메세지를 만들어준다)
			});
			throw new CustomException("Validation Failed", errorMap);
		}
		
		return joinPoint.proceed(); //proceed 실행되면 authenticationService.checkDuplicatedEmail(signupReqDto.getEmail()); 실행
	}
}
