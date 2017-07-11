package com.registration.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

// no touch db, hibernate do everything

@Entity
@Table(name="Teacher")
public class Teacher {
	
	// now we do not need to create a table in db
	// we use just hibernate annotation
	//table will be created by hibernate
	
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
	
	// Cascade: save, update, delete(no)
	// save session.save() calls
	
// Fetch:fetch select, EAGER(no)
	
	// foreign key can be used to create relationships 
	// among tables
	
	// one teacher has multiple courses
	
	//?One Teacher To Many courses
	// One is here
	// To is my destination
	
	// FetchType.LAZY means: by default: no access to related table
	// EAGER: records in connecting table can be retrieved after session closed
	// but never use EAGER!
	@OneToMany(mappedBy="teacher", fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Course> courses;
	
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
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}	

}

// Teacher - Courses
// none- foreign key mapping teacherId: menas this course
// belongs to this one teacher only
// use joins to retrieve data

// inner join: between pk and fk
