package com.firsthuanya.payment.service;

public class OpenSessionException extends Exception{

	public OpenSessionException(String string){
		super(string);
	}
	
	public OpenSessionException(String string,Exception e){
		super(string,e);
	}
}
