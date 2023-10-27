package com.tcs.web.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.model.Questions;
import com.tcs.web.repo.QuestionRepo;

@Service
@Transactional
public class QuestionService {
	
	@Autowired
	private QuestionRepo QuestionRepo;
	
	public void saveQuestion(Questions ques)
	{
		QuestionRepo.save(ques);
	}
	
	
	

}
