package com.tcs.web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcs.web.model.Marks;

@Repository
public interface MarksRepo extends JpaRepository <Marks, Integer> {
	@Query("select m from Marks m where m.id=:keyword")
	public Marks findResultByKeyword(@Param("keyword") String keyword);
}
