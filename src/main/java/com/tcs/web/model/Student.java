package com.tcs.web.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Student {
	private int id;
	private String name;
	private String email;
	private String phoneno;
	private String school;
	private String clas;
	private String marks;
	private String division;
	private String year;
	private String course;
	private String dob;
	private String religion;
	private String nationality;
	private String Address;
	private String guardian;
	private String caste;
//	private MultipartFile file;
	private LocalDateTime date_of_add;
	///private int total;
	
	
	
/* public MultipartFile getFile() {	
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	} */

/*
 * public int getTotal() { return total; }
 * 
 * public void setTotal(int total) { this.total = total; }
 */

	public LocalDateTime getDate_of_add() {
		return date_of_add;
	}

	public void setDate_of_add(LocalDateTime dt) {
		this.date_of_add = dt;
	}

	public String getGuardian() {
		return guardian;
	}

	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phone) {
		this.phoneno = phone;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}



	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	
	

	public Student() {
	   }

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
