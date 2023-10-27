package com.tcs.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.web.model.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {

}
