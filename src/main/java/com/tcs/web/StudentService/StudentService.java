package com.tcs.web.StudentService;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.SMS.sendSms;
import com.tcs.web.export.AckReceipt;
import com.tcs.web.model.Student;
import com.tcs.web.repo.studentRepo;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	studentRepo studentrepo;
	@Autowired
	sendSms sms;
	
	public int studentRegistration(Student std)
	{
		
		studentrepo.save(std);
		System.out.println("Student info  saved in DB ");
		String msg=sms.sendSms();
		int id= studentrepo.findByIdMax();
		System.out.println("send SMS status "+msg);
		System.out.println("Name of last student id "+id);
		Student stdt= studentrepo.findById(id);
		//studentrepo.findById(id);
			
		System.out.println("Name of last student "+stdt.getName());
		return id;
	}
	
	@Autowired
	studentRepo stdrepo;
	public void saveEditStudent(Student std)
	{	stdrepo.save(std);
		System.out.println("Student saved after edidting ");
	}
	
	@Autowired
	studentRepo studentrepo2;
	public Student studentNameId(int id)
	{
		Student stdt= studentrepo2.findById(id);
		return stdt;
	}
	
	@Autowired
	studentRepo studentrepo1;
	public int ackNo()
	{
		System.out.println("Retrieving ack no from DB ");
		int id=studentrepo.findByIdMax();
		return id;
	
	}
	
	public List<Student> showRegisteredStudent()
	{
		List<Student> std=studentrepo.findAll();
		Long total= studentrepo.count();
		System.out.println("Total Students"+total);
		
		return std;
	}

	
	public List<Student> findByKeyword(String keyword)
	{
	 
	 System.out.println("Search inside service  "+keyword);
	 List<Student> std= studentrepo.findByKeyword(keyword);
	 System.out.println("Search students "+std);
	 for(Student st:std)
		{	
		System.out.println("Search students from loop "+st.getName());
		}
	 
	    return std;
	}
	
	@Autowired
	studentRepo studentrepo3;
	public Student showEditStudentPage(int id)
	{
		System.out.println("Student edit repo called");
		Student std=studentrepo3.findById(id);
		return std;
	}
	
	/*
	 * public Path passFileName(Path path) { AckReceipt ack =new AckReceipt();
	 * ack.passFileName(path); return path; }
	 */
}
