package com.leverage.util;

public class InternalServerException extends Exception {

	private static final long serialVersionUID = 1L;

	public InternalServerException(){}
	
	public InternalServerException(String message)
	{
		super(message);
	}
}
