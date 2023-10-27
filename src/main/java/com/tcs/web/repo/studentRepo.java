package com.tcs.web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcs.web.model.Student;

@Repository
public interface studentRepo extends JpaRepository <Student, Integer>{

	 @Query("select max(t.id) from Student t")
	  public int findByIdMax();
     
	@Query("select t from Student t where t.id=:id") 
	public Student findById(int id);
	
	@Query("select s from Student s where s.name LIKE %:keyword% or s.id LIKE %:keyword%")
	 public List<Student> findByKeyword(@Param("keyword") String keyword);
	

	

	
	
	
	
     
	
}
