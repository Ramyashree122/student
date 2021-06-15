package com.student.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.oito.student.dao.ValidationException;
import com.oito.student.vo.Student;

public class StudentValidator 
{
	Logger log = LoggerFactory.getLogger(StudentValidator.class);
	public void validateId(int id) throws ValidationException 
	{
		if(id<=0) 
		{
			log.error("Invalid Id"+id);
			throw new ValidationException("Entered ID is invalid");
		}
	}
	
	public void validateString(String string) throws ValidationException 
	{
		String format="^[a-zA-Z\\s]*$";
		Pattern pattern = Pattern.compile(new String (format));
	    Matcher matcher = pattern.matcher(string);
	    if(!matcher.matches())
	    {
	    	log.error("invalid string"+string);
	    	throw new ValidationException("Entered String is invalid");
	    }
	}
	
	public void validator(Student student) throws ValidationException
	{
		if(student.getStudentId()<=0)
		{
			log.error("Invalid Id"+student.getStudentId());
			throw new ValidationException("Entered id is invalid");
		}
		
		String format="^[a-zA-Z\\s]*$";
		Pattern pattern = Pattern.compile(new String (format));
	    Matcher matcher = pattern.matcher(student.getName());
	    if(!matcher.matches())
	    {
	    	log.error("invalid string"+student.getName());
	    	throw new ValidationException("Entered String is invalid");
	    }
		
		
	}
	
}
