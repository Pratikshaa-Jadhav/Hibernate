package com.Anudip.hibernatejunittesting;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="student3")

public class Student3 {
@Id

	private int sid;
	private String sname;
	private String scontact;
	private String email;
	public Student3() {
		
		// TODO Auto-generated constructor stub
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getScontact() {
		return scontact;
	}
	public void setScontact(String scontact) {
		this.scontact = scontact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Student3(int sid, String sname, String scontact, String email) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.scontact = scontact;
		this.email = email;
	}
	
}
