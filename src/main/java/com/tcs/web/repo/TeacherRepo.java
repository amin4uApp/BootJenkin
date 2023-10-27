package com.tcs.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.web.model.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {

}
