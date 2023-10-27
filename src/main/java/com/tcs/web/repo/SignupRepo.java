package com.tcs.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.web.model.Signup;


public interface SignupRepo extends JpaRepository<Signup, Long> {

}
