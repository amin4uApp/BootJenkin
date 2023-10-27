package com.tcs.web.ContactService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.model.Contact;
import com.tcs.web.repo.ContactRepo;

@Service
@Transactional
public class ContactService {
	
	List<Contact> cont=null;
		@Autowired
		ContactRepo repot;
		
	 public int savefeedBack(Contact contact)
	 {
		 repot.save(contact);
		 System.out.println("FeedBack saved in DB ");
		 int id=repot.findByIdMax();
		 return id;
		 
	 }
	 public List<Contact> getAllFeedBack(Contact contact)
	 {
		 System.out.println("Before Repo call");
		 cont=repot.findAll();
		 System.out.println("all FeedBack from  DB ");
		 return cont;
		 
	 }
}
