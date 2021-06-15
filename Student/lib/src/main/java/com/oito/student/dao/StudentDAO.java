package com.oito.student.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oito.student.vo.Student;

public class StudentDAO 
{
	Logger log = LoggerFactory.getLogger(StudentDAO.class);
	
	private static Connection connection=null;
			
	
	public boolean insert(Student student) throws CustomException
	{
		connection=new DataBaseManager().getConnection();
		boolean status=false;
		try
		{
			PreparedStatement prepareStatement=connection.prepareStatement("insert into student(student_id,student_name,student_age) values (?,?,?)");
			prepareStatement.setInt(1, student.getStudentId());
			prepareStatement.setString(2, student.getName());
			prepareStatement.setInt(3, student.getAge());
			status=prepareStatement.execute();
		} 
		catch (Exception e)
		{
			log.error("The data entered is not valid enter the valid data"+e);
			throw new CustomException("invalid input");
		}
		finally
		{
			if(status)
			{
				try 
				{
					connection.commit();
				} 
				catch (SQLException e) 
				{
					log.error("unadble to commit the operation"+e);
					throw new CustomException("unable to commit"+e);
				}
			}
			else
			{
				try
				{
				connection.rollback();
				}
				catch (SQLException e) 
				{
					log.error("unadble to rollback the operation"+e);
					throw new CustomException("unable to rollback"+e);
				}
			}
			try
			{
			connection.close();
			}
			catch (SQLException e) 
			{
				log.error("unadble to close the connection"+e);
				throw new CustomException("unable to close the connection"+e);
			}
		}
		return status;
	}
	
	public boolean delete(int id) throws CustomException
	{
		connection=new DataBaseManager().getConnection();
		boolean status=false;
		try
		{
			PreparedStatement prepareStatement=connection.prepareStatement("delete from student where student_id=? ");
			prepareStatement.setInt(1, id);
			status=prepareStatement.execute();
		}
		catch (Exception e)
		{
			log.error("invalid student id"+e);
			throw new CustomException("invalid student id");
		}
		finally
		{
			if(status)
			{
				try 
				{
					connection.commit();
				} 
				catch (SQLException e) 
				{
					log.error("unadble to commit the operation"+e);
					throw new CustomException("unable to commit"+e);
				}
			}
			else
			{
				try
				{
				connection.rollback();
				}
				catch (SQLException e) 
				{
					log.error("unadble to rollback the operation"+e);
					throw new CustomException("unable to rollback"+e);
				}
			}
			try
			{
			connection.close();
			}
			catch (SQLException e) 
			{
				log.error("unadble to close the connection"+e);
				throw new CustomException("unable to close the connection"+e);
			}
		}
		return status;
	}
	
	public boolean update(Student student) throws  CustomException
	{
		connection=new DataBaseManager().getConnection();
		boolean status=false;
		try
		{
			PreparedStatement prepareStatement=connection.prepareCall("update student set student_name=?,student_age=? where student_id=?");
	        prepareStatement.setString(1, student.getName());
	        prepareStatement.setInt(2, student.getAge());
	        prepareStatement.setInt(3, student.getStudentId());
	        status=prepareStatement.execute();
		}
		catch (Exception e)
		{
			log.error("id not found"+e);
			throw new CustomException("id not found");
		}
		finally
		{
			if(status)
			{
				try 
				{
					connection.commit();
				} 
				catch (SQLException e) 
				{
					log.error("unadble to commit the operation"+e);
					throw new CustomException("unable to commit"+e);
				}
			}
			else
			{
				try
				{
				connection.rollback();
				}
				catch (SQLException e) 
				{
					log.error("unadble to rollback the operation"+e);
					throw new CustomException("unable to rollback"+e);
				}
			}
			try
			{
			connection.close();
			}
			catch (SQLException e) 
			{
				log.error("unadble to close the connection"+e);
				throw new CustomException("unable to close the connection"+e);
			}
		}
		return status;
	}
	
	
	
	public List<Student> searchByName(String name) throws CustomException
	{
		connection=new DataBaseManager().getConnection();
		List<Student> studentList=new ArrayList<>();
		try
		{
			PreparedStatement prepareStatement=connection.prepareStatement("select * from student where student_name like '%"+name+"%'");
			ResultSet resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				studentList.add(new Student(resultSet.getInt("student_id"),resultSet.getString("student_name"),resultSet.getInt("student_age")));
			}
		}
		catch(Exception e)
		{
			log.error("No matching records found"+e);
			throw new CustomException("No matching records found");
		}
		finally
		{
			try
			{
			connection.close();
			}
			catch (SQLException e) 
			{
				log.error("unadble to close the connection"+e);
				throw new CustomException("unable to close the connection"+e);
			}
		}
		return studentList;
	}
	
	public List<Student> getStudentDetails(int limitValue,int offSetValue) throws CustomException
	{
		connection=new DataBaseManager().getConnection();
		List<Student> studentList=new ArrayList<>();
		PreparedStatement prepareStatement;
		try {
			connection.setAutoCommit(false);
			prepareStatement = connection.prepareStatement("select * from student LIMIT ? OFFSET ?");
			prepareStatement.setInt(1, limitValue);
			prepareStatement.setInt(2, offSetValue);
			ResultSet resultSet=prepareStatement.executeQuery();
			
			while(resultSet.next())
			{
				studentList.add(new Student(resultSet.getInt("student_id"),resultSet.getString("student_name"),resultSet.getInt("student_age")));
			}
		} 
		catch(Exception e)
		{
			throw new CustomException("no records found at this limit and offset");
		}
		finally
		{
			try
			{
			connection.close();
			}
			catch (SQLException e) 
			{
				log.error("unadble to close the connection"+e);
				throw new CustomException("unable to close the connection"+e);
			}
		}
	    return studentList;
	}
}
