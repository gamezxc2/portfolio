package com.tj.exception;

public class PasswordMissmatchException extends Exception{
	private String message;
	public PasswordMissmatchException() {	
		this.message = "비밀번호가 달라요."; // 기본 메시지. 
	}
	public PasswordMissmatchException(String msg){ // 따로 메시지를 입력하여 출력할 경우.
		this.setMessage(msg);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
