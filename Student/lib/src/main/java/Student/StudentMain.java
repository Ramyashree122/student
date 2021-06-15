package Student;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oito.student.dao.CustomException;
import com.oito.student.dao.ValidationException;
import com.oito.student.vo.Student;
import com.student.service.StudentService;

public class StudentMain 
{
	public static void main(String[]args) throws CustomException, ValidationException
	{
		Logger log = LoggerFactory.getLogger(StudentMain.class);
		StudentService studentService =new StudentService();
		Scanner read=new Scanner(System.in);
		
		while(true)
		{
			log.info("\nEnter 1:insert the student details\n2:update student details\n3:delete student details\n4:search student details by name\n5:Search student details by pagination\n");
			int choice=read.nextInt();
			
			switch(choice)
			{
			case 1:
				log.info("Enter the id,name,age of the student");
				studentService.insert(new Student(read.nextInt(),read.next(),read.nextInt()));
				break;
			case 2:
				log.info("Enter the id of the student whose data has to be updated");
				int id=read.nextInt();
				System.out.println("Enter the update name and age of the student");
				String name=read.next();
				int age=read.nextInt();
				studentService.update(new Student(id,name,age));
				break;
			case 3:
				log.info("Enter the id of the student whose data has to be deleted");
				studentService.delete(read.nextInt());
				break;
			case 4:
				log.info("Enter the name of the student whose data has to be searched");
				studentService.searchByName(read.next());
				break;
			case 5:
				log.info("Enter the limit and offset values");
				studentService.studentDetails(read.nextInt(),read.nextInt());
				break;
			default:
				read.close();
			}
		}
	}
}
