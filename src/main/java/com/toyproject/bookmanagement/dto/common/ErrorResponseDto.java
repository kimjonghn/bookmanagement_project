package com.toyproject.bookmanagement.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto<T> { //메세지랑 에러데이터(에러Map)전달할수 있는용도
	private String message;
	private T errorData; //(errorMap)
}
