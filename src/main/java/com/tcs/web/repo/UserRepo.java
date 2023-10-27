package com.tcs.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.web.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
