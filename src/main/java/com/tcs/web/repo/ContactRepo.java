package com.tcs.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcs.web.model.Contact;

@Repository
public interface ContactRepo  extends JpaRepository<Contact, Long> {

	 @Query("select max(t.id) from Contact t")
	  public int findByIdMax();
	 
}
