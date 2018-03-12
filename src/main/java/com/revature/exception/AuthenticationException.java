package com.revature.exception;

public class AuthenticationException extends Exception{
	public AuthenticationException(){
		
	}
	public AuthenticationException(String message){
		super(message);
		System.out.println(message);
	}
	

}
