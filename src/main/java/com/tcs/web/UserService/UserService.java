package com.tcs.web.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.model.User;
import com.tcs.web.repo.UserRepo;

@Service
@Transactional
public class UserService {
	
	//User saving to repo/DB
	@Autowired
	private UserRepo UserRepo;
	
	public void saveUser(User user)
	{
		UserRepo.save(user);
		
		return;
	 	
	}

}
