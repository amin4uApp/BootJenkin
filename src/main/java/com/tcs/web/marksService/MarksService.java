package com.tcs.web.marksService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.model.Marks;
import com.tcs.web.model.Student;
import com.tcs.web.repo.MarksRepo;
import com.tcs.web.repo.studentRepo;

@Service
@Transactional
public class MarksService {
	
	double avg;
	int total;
	Marks mark1;
	
 @Autowired
 MarksRepo markRepo;
 @Autowired
 studentRepo std_course;
 
	public Marks findResultByKeyword(String keyword)
	{
		String id = null;
	 System.out.println(" id for result search  "+keyword);
	 Marks mark= markRepo.findResultByKeyword(keyword);
	 // id =mark.getId();
	 //int i=Integer.parseInt(id); 
	 //System.out.println(" Integer id from string   "+id);
	 //Student corse=std_course.findById(i);
	// System.out.println(" Student  "+corse.toString());
	   if(mark==null)
		   return mark;
		 
		if(mark.getCourse().equalsIgnoreCase("ADCA"))  
		{ 
		   total=mark.getSub1()+mark.getSub2()+mark.getSub3()+mark.getSub4()+mark.getSub5()+mark.getSub6()+mark.getSub7();
		   mark.setTotal(total);
		   avg=(double)total/7;
		   mark.setAvg(avg);
		   System.out.println("Total Marks of  students for ADCA "+total);
	    }
	   
		else
			if(mark.getCourse().equalsIgnoreCase("DCA"))
			{
				 total=mark.getSub1()+mark.getSub2()+mark.getSub3()+mark.getSub4()+mark.getSub5();
				   mark.setTotal(total);
				   avg=(double)total/5;
				   mark.setAvg(avg);
				   System.out.println("total Marks of students for DCA "+total);
			}
	
	
		 return mark;
	
}
	
}
	

	  
	 
	 
	
	


