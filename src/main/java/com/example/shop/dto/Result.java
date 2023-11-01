package com.example.shop.dto;

import lombok.Data;

@Data
public class Result {
	private int code;
	private String msg;
	private Object data;
	
	public Result() {
		
	}
	
	public Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Result(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

}
