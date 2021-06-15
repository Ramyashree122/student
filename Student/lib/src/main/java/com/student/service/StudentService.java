package com.student.service;

import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oito.student.dao.CustomException;
import com.oito.student.dao.StudentDAO;
import com.oito.student.dao.ValidationException;
import com.oito.student.vo.Student;

public class StudentService 
{
	Logger log = LoggerFactory.getLogger(StudentService.class);
	StudentValidator validation = new StudentValidator();
	StudentDAO studentDAO=new StudentDAO();
	
	public void insert(Student student) throws CustomException, ValidationException
	{
		validation.validator(student);
		studentDAO.insert(student);
		log.info("inserted");
	}
	
	public void delete(int id) throws CustomException, ValidationException
	{
		validation.validateId(id);
		studentDAO.delete(id);
		log.info("Deleted");
	}
	
	public void update(Student student) throws CustomException, ValidationException
	{
		validation.validator(student);
		studentDAO.update(student);
		log.info("Updated");
	}
	
	public void searchByName(String name) throws CustomException, ValidationException
	{
		validation.validateString(name);
		List<Student> studentList=studentDAO.searchByName(name);
		ListIterator<Student> iterator=studentList.listIterator();
		log.info("student id\tstudent name\tstudent age\n");
		while(iterator.hasNext())
		{
			Student student=iterator.next();
			log.info(+student.getStudentId()+"\t"+student.getName()+"\t"+student.getAge());
		}
	}
	
	public void studentDetails(int limitValue,int offSetValue) throws CustomException
	{
		List<Student> studentList=studentDAO.getStudentDetails(limitValue,offSetValue-1);
		ListIterator<Student> iterator=studentList.listIterator();
		log.info("student id\tstudent name\tstudent age\n");
		while(iterator.hasNext())
		{
			Student student=iterator.next();
			log.info(+student.getStudentId()+"\t"+student.getName()+"\t"+student.getAge());
		}
	}

	
}
