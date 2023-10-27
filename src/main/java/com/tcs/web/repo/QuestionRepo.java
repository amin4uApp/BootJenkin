package com.tcs.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.web.model.Questions;

public interface QuestionRepo extends  JpaRepository <Questions, Integer>{

}
