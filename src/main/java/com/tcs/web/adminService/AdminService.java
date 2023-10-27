package com.tcs.web.adminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.model.Admin;
import com.tcs.web.repo.AdminRepo;

@Service
@Transactional
public class AdminService {
	
	
	@Autowired
	private AdminRepo repot;	
	
	public void adminUser(Admin admin)
	{
		System.out.println(" Admin Repo Called ");
		repot.save(admin);
		
		
	}

}
