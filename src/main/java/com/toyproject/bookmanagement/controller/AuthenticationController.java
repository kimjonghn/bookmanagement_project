package com.toyproject.bookmanagement.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.bookmanagement.aop.annotation.ValidAspect;
import com.toyproject.bookmanagement.dto.auth.SignupReqDto;
import com.toyproject.bookmanagement.service.AuthenticationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(){
		return ResponseEntity.ok(null);
	}
	
	@CrossOrigin
	@ValidAspect //signup메서드가 호출이되면 ValidAspectaop 실행
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult){ //@Valid-에러가 있는지 체크 오류가 있으면 BindingResult를 만들어준다
		authenticationService.checkDuplicatedEmail(signupReqDto.getEmail()); //email중복확인
		
		return ResponseEntity.ok(null);
	}
}
