package com.registration.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="Course")
public class Course {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="courseName")
	private String courseName;
	
	@Column(name="courseDate")
	private Date courseDate;
	
	//one course can only have one teacher
	
	//Many is here
	//?To is my destination
	//use JoinColumn is enought
	@ManyToOne
	@JoinColumn(name="teacher_id")
	
	//all affected
	@Cascade(CascadeType.ALL)
	private Teacher teacher;
	
	//many to many relationship use mappedBy, created a new table
	@OneToMany(mappedBy="course")
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Date getCourseDate() {
		return courseDate;
	}
	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}	

}
