package com.tcs.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marks {
	private String id;
	private String name;
	private String rollNo;
	private int sub1;
	private int sub2;
	private int sub3;
	private int sub4;
	private int sub5;
	private int sub6;
	private int sub7;
	private int total;
	private double avg;
	private String remarks;
	private String course;
	
   public Marks()
   {}
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getId() {
		return id;
	}
	
	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public int getSub1() {
		return sub1;
	}

	public void setSub1(int sub1) {
		this.sub1 = sub1;
	}

	public int getSub2() {
		return sub2;
	}

	public void setSub2(int sub2) {
		this.sub2 = sub2;
	}

	public int getSub3() {
		return sub3;
	}

	public void setSub3(int sub3) {
		this.sub3 = sub3;
	}

	public int getSub4() {
		return sub4;
	}

	public void setSub4(int sub4) {
		this.sub4 = sub4;
	}

	public int getSub5() {
		return sub5;
	}

	public void setSub5(int sub5) {
		this.sub5 = sub5;
	}
	public int getSub6() {
		return sub6;
	}

	public void setSub6(int sub6) {
		this.sub6 = sub6;
	}


	public int getSub7() {
		return sub7;
	}

	public void setSub7(int sub7) {
		this.sub7 = sub7;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getAvg() {
		return avg;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
}
