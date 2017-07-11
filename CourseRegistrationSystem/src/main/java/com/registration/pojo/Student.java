package com.registration.pojo;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="student")
public class Student {
	
	// hibernate do not work with sql
	// instead it works with pojo classes
	// mapping to tables
	
	// you can use .xml or annotation to configure the mapping
	// annotation is better; use one only;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phoneNo")
	private String phoneNo;
	
	// suppose student table gets updated,
	// all tables related to student get updated,
	@OneToMany(mappedBy="student")
	@Cascade(CascadeType.ALL)
	// Cascade: SAVE_UPDATE or DELETE
	// we choose SAVE_UPDATE, not DELETE(dangerous)
	private List<StudentCourseMapping> studentCourseMapping;
	
	public List<StudentCourseMapping> getStudentCourseMapping() {
		return studentCourseMapping;
	}
	public void setStudentCourseMapping(List<StudentCourseMapping> studentCourseMapping) {
		this.studentCourseMapping = studentCourseMapping;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
