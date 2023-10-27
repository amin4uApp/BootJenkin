 package com.tcs.web.signupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.model.Signup;
import com.tcs.web.repo.SignupRepo;

@Service
@Transactional
public class SignupService {
	@Autowired
	SignupRepo repo;
		public void signUpsave(Signup signup)
	{
		
		System.out.println("Saving sign up repo");
		repo.save(signup);
		
		
		
	}

}