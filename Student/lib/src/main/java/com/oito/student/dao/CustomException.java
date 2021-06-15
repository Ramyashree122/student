package com.oito.student.dao;

public class CustomException extends Exception 
{

	private static final long serialVersionUID = 1;
	public CustomException(String message) 
	{
		super(message);
	}
}