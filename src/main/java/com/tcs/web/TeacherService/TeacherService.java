package com.tcs.web.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tcs.web.model.Teacher;
import com.tcs.web.repo.TeacherRepo;

@Service
@Transactional
public class TeacherService {
	
	@Autowired
	TeacherRepo repot;
	
 public void saveTeacher(Teacher teacher)
 {
	 repot.save(teacher);
	 
 }
  
 
}
