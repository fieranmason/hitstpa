package com.leverage.util;

public class ReferentialIntegrityException extends Exception {

	private static final long serialVersionUID = 1L;

	public ReferentialIntegrityException(){}
	public ReferentialIntegrityException(String message)
	{
		super(message);
	}
}
