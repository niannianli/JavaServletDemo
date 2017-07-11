package com.registration.pojo;

//this class is created to save data in object, and retrieve data in object
// without differnt queries to database
public class TeacherVO {

	private String name;
	private String phoneNo;
	private String courseName;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}	
	
}