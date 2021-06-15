package com.oito.student.dao;

import lombok.AllArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
public class ValidationException extends Exception{
  
	private String msg;
	
	@Override
	public String toString() 
	{
		return msg;
	}
}
