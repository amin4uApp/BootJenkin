package com.tcs.web.adminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.model.Admin;
import com.tcs.web.model.Login;
import com.tcs.web.model.Signup;
import com.tcs.web.repo.AdminRepo;
import com.tcs.web.repo.SignupRepo;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private SignupRepo signrepo;
	private String id;
	private String passwd;
	
	public String loginUser(Login login)
	{
		id=login.getUserid();
		passwd=login.getPassword();
		//System.out.println(" The password is "+passwd);
		
		List<Signup> list= signrepo.findAll();
		//System.out.println(" users list from sign up  "+list.toString());
		    for(Signup sign : list)
		   	 {
		   		// System.out.println("The data are "+sign.getEmail() );
		   		 if(id.equalsIgnoreCase(sign.getEmail())&& passwd.equals(sign.getPassword()))
		   		 {
		   			 return "loginsuccess";
		   		 }
		   		 
		   		 else if(id.equalsIgnoreCase("admin@test.com")&& passwd.equals("Manik@123#"))
		   		 {
		   			 return "admin1";
		   		 }
		   	 }
		    return "failed";
		      
		       
	}
	
	}
