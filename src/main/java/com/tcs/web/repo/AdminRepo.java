package com.tcs.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.web.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long> {

}
