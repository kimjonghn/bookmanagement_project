package com.toyproject.bookmanagement.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class SignupReqDto {
	@Email
	private String email;
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", //정규식(시작^ 끝&) (//d == 0-9)=숫자를 포함했는지 {8,16} = 8자이상 16자이하
			message = "비밀번호는 영문자, 숫자, 특수문자를 포함하여 8 ~ 16자로 작성") 
	private String password;
	
	@Pattern(regexp = "^[가-힣]*${2,7}", message = "한글이름만 작성가능합니다.") //한글만되고 2 ~ 7글자까지 가능
	private String name;
}
