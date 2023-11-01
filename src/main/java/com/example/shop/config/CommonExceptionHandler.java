package com.example.shop.config;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.shop.dto.Result;

@RestControllerAdvice
public class CommonExceptionHandler {
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		StringBuilder sb = new StringBuilder("校验失败。");
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			sb.append("[").append(fieldError.getField()).append(fieldError.getDefaultMessage()).append("]");
		}
		return new Result(0, sb.toString());
	}
}
