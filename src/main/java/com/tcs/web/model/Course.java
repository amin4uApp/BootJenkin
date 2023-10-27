package com.tcs.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Course {
	
	private Long id;
	private String name;
	private int duration;
	private int fee;
	
	 public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public Course() {
	   }

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   public Long getId() {
	       return id;
	   }

	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}
