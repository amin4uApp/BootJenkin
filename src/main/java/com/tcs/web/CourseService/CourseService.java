package com.tcs.web.CourseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.model.Course;
import com.tcs.web.repo.CourseRepo;

@Service
@Transactional
public class CourseService {
	@Autowired
	CourseRepo repo;
	
	  public List<Course> listAll() {
	        return repo.findAll();
	    }

	  public void saveCourse(Course course)
	  {
		  System.out.println("Service Class"+course.getName());
		  repo.save(course);
	  }
	  
	  public Course get(long id) {
	        return repo.findById(id).get();
	    }
	     
	    public void delete(long id) {
	        repo.deleteById(id);
	    }
}
