package com.revature.exception;

public class InsufficientAmountException extends Exception {
	public InsufficientAmountException(){}
	
	public InsufficientAmountException(String message){
		super(message);
		System.out.println(message);
	}

}
