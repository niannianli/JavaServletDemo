package com.registration.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.registration.pojo.Address;
import com.registration.pojo.Course;
import com.registration.pojo.Student;
import com.registration.pojo.StudentCourseMapping;
import com.registration.pojo.Teacher;
import com.registration.pojo.TeacherVO;
import com.registration.pojo.User;
import com.registration.util.HibernateUtil;

public class Main {

	// no matter how many times getSessionFactory is called
	// only one session factory is used and created

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = null;
		session = factory.openSession();
		session.beginTransaction();

		// create objects here, instead of using INSERT statement, hibernate
		// will help insert the data
		createTeachersCoursesStudents();

		// retrieve data by id
		// get(): hibernate: always hit the db
		Teacher t = (Teacher) session.get(Teacher.class, 1);
		// load: if same object previously retrieved,
		// it will not hit db again
		// faster
		Teacher t6 = (Teacher) session.load(Teacher.class, 1);

		// hql does not use table, it uses
		// : entity class, and variable name

		// better to use ? or :address, safer
		Query query = session.createQuery("from Teacher where address =:address");
		Query query1 = session.createQuery("select name, phoneNo from Teacher where address = :address");
		// to improve query performance, always use select to retriev columns
		// you need only

		// hibernate udpate
		Query query2 = session
				.createQuery("select name as name, phoneNo as phoneNo from Teacher where address = :address");
		Query query3 = session.createQuery("update Teacher set name = :name where address = :address");
		query3.setParameter("name", "newName");
		query3.setParameter("address", "newAddress");
		int rows = query3.executeUpdate();

		// join, pk fk,
		// I need a list of courses with teacher info
		// sql: select t1.name, t2.courseName from teacher t1 inner join course
		// t2
		// on t1.id = t2.teacher_id;

		// hql join
		// join: one to many; many to one; works
		// many to many: no: need another table

		// we did not mention course entity at all,
		// but we got the list of courses through OneToMany annotation
		Query query4 = session.createQuery("select T1.name as name, T1.phoneNo as phoneNo, "
				+ "T2.courseName as courseName from Teacher" + " as T1 inner join T1.courses as T2");

		// save data in object
		query4.setResultTransformer(Transformers.aliasToBean(TeacherVO.class));

		// safer this way
		// query.setParameter("address", "PA");

		// transform our object(partial data) to class
		// query.setResultTransformer(Transformers.aliasToBean(TeacherVO.class));
		// : table, and column
		// sql: select * from teacher where address = 'PA';

		// so we only get part data, not a whole Teacher
		// List<Teacher> list = query.list();
		// List<Object> list = query.list();

		List<TeacherVO> list = query4.list();

		// for(Teacher t : list){
		for (TeacherVO vo : list) {
			// System.out.println(t.getId());

			// convert an object to an array
			// 0 index: name
			// 1 index: phoneNo
			// Object[] objects = (Object[]) o;
			System.out.println(vo.getName() + "..." + vo.getPhoneNo() + "..." + vo.getCourseName());
		}

		// criteria can only be used to retrieve data, not others
		System.out.println("criteria");

		String userRole = "Admin";
		Criteria criteria = session.createCriteria(Teacher.class);

		// dynamic query with criteria
		// join with criteria?
		// hql with criteria.

		if (userRole == "Admin") {
			criteria.add(Restrictions.eq("address", "CA"));
		} else {
		}

		// add conditions/restrictions
		// where address = 'CA'
		// criteria.add(Restrictions.eq("address", "CA"));

		List<Teacher> list1 = criteria.list();

		for (Teacher t1 : list1) {
			System.out.println(t1.getName());
		}

		// what if table contains hundreds of columns? too slow. what to do?
		// in order to retrieve a particular column, not other columns

		// fetch strategy

		Course c1 = (Course) session.get(Course.class, 1);
		Course c2 = (Course) session.get(Course.class, 2);
		Course c3 = (Course) session.get(Course.class, 3);

		// only need to update course
		// teacher will be updated too, becasue
		// teacher is used in course
		// and we are using cascade
		// saving up sesssion.save();

		// create 2 new students
		Student s1 = new Student();
		s1.setName("S45");
		s1.setAddress("Ireland");
		s1.setPhoneNo("888");

		Student s2 = new Student();
		s2.setName("S46");
		s2.setAddress("Scotland");
		s2.setPhoneNo("777");

		// 2 courses already exist,
		Course javaCourse = (Course) session.get(Course.class, 1);
		Course sqlCourse = (Course) session.get(Course.class, 2);

		// let 2 students attend both courses
		// 4 rows in total now
		StudentCourseMapping scm1 = new StudentCourseMapping();
		scm1.setStudent(s1);
		scm1.setCourse(javaCourse);

		StudentCourseMapping scm2 = new StudentCourseMapping();
		scm2.setStudent(s2);
		scm2.setCourse(javaCourse);

		StudentCourseMapping scm3 = new StudentCourseMapping();
		scm3.setStudent(s1);
		scm3.setCourse(sqlCourse);

		StudentCourseMapping scm4 = new StudentCourseMapping();
		scm4.setStudent(s2);
		scm4.setCourse(sqlCourse);

		List<StudentCourseMapping> list3 = new ArrayList<StudentCourseMapping>();
		list3.add(scm1);
		list3.add(scm3);
		s1.setStudentCourseMapping(list3);

		List<StudentCourseMapping> list2 = new ArrayList<StudentCourseMapping>();
		list2.add(scm2);
		list2.add(scm4);
		s2.setStudentCourseMapping(list2);

		// no id, insert new
		// id exists/passes, update old
		session.saveOrUpdate(s1);
		session.saveOrUpdate(s2);

		// if delete student
		// then mapping deleted too.
		// session.delete(s1);

		// session.saveOrUpdate(scm1);
		// session.saveOrUpdate(scm2);
		// session.saveOrUpdate(scm3);
		// session.saveOrUpdate(scm4);

		// we used too many sessino.save()
		// is there an easier way?

		// cascading: when inserting, related tables will also
		// be updated
		// no need to update all tables manually

		// JUST save student/course, then mapping tables updated
		// automatically

		/**
		 * session.save(t1);
		 * 
		 * session.save(t2);
		 * 
		 * session.save(c1);
		 * 
		 * session.save(c2);
		 * 
		 * session.save(c3);
		 */

		// Teacher t1 = (Teacher)session.get(Teacher.class, 1);
		// Teacher t2 = (Teacher)session.get(Teacher.class, 2);

		// needs access to course table
		// List<Course> courses = t1.getCourses();

		session.getTransaction().commit();
		session.close();
	}

	public static void createTeachersCoursesStudents() {

		Teacher t1 = new Teacher();
		t1.setName("T1");
		t1.setAddress("PA");
		t1.setPhoneNo("1");

		Teacher t2 = new Teacher();
		t2.setName("T2");
		t2.setAddress("CA");
		t2.setPhoneNo("2");

		Course c1 = new Course();
		c1.setCourseName("java");
		c1.setCourseDate(new Date());
		c1.setTeacher(t1);

		Course c2 = new Course();
		c2.setCourseName("sql");
		c2.setCourseDate(new Date());
		c2.setTeacher(t1);

		Course c3 = new Course();
		c3.setCourseName("jquery");
		c3.setCourseDate(new Date());
		c3.setTeacher(t2);

	}// end of method

}// end of class

// HQL tutorial

// join
// select * from user as t1 inner join address as t2 on t1.id =
// t2.user_id;
// select t1.name, t2.address from user as t1 inner join address as t2
// on t1.id = t2.user_id;

// Learn:
// how to use HQL?
// w3c school check :
// procedure?some queries/sqls
// function?calculation
// view?better use object class
// trigger?automatic procedure
// cursor?cursor is a control structure that enables traversal over the records
// in a database. ...
// In SQL procedures, a cursor
// makes it possible to define a result set (a set of data rows)
// and perform complex logic on a row by row basis.

// url sent request to google web server: what to do with the request?
// w3schools.com:
// reqeust to another url
// click button request sent to web server:
// web server return a response url

// whats on url? generate html code
// html code
// css for style

// we need local tomcat server here too
// http: protocol:

// server: a group of computers:
// install some applications:
// which is web server:
// deploy java application to web server
// 24/7
// web server:
// tomcat;
// jetty
// glassfish
// jboss

// runn web app on tomcat server
// servlets
// form : html

// transient state: object created with data
// persistence state: object get associated with session: session.save(student);

// change data after session: data is changed
// is accepted in database
// whatever change is accepted

// but once i close the session?
// detached state: nothing can be done with the object
// no change is accpeted in db

// create object
// insert object

// entity class-table
// session: calls connection to db
// begin db handle

// auto_increment in db
// id should be auto_increment

// ALTER TABLE student MODIFY COLUMN id int(11) auto_increment
// ALTER TABLE tbl DROP COLUMN id;
// ALTER TABLE tbl ADD id INT
// GRANT ALL ON student TO 'root'@'localhost';

// cd /usr/local/mysql/bin
// ./mysql -u root -p

// session class
// use id to update data
// delete too by id

// mapping
// 1-1
// 1-many
// many-1

// db-db

// ERD: entity relationship diagram

// entity->table

// Student:
// Class: java sql (id, )
// Teacher:

// Students--Classes: many--many
// Teachers--Classess:1--many

// students-courses
// many-many

// a third is needed for mapping
// id(pk)
// student_id(fk)
// course_id(fk)

// to connect students and courses
// for many to many

// one object
// retrieve by ID
// User user1 = (User) session.get(User.class, 18);
// System.out.println(user1.getName());

// user1.setAddress("HA11");
// update: if do not update, it will not update
// even after set
// session.update(user1);
// session.delete(user1);

// no sql, data inserted
// User user = new User();
// user.setName("Li");
// user.setAddress("uk");
// user.setUsername("liuser");
// user.setPassword("lips");
// session.save(user);

// how to retrieve all the data?
// we can only retrieve by id before
// we have to use sql to do this: HQL: Hibernate SQL

// User here is not table, it is our pojo class
// similar to SQL, minor difference

// System.out.println(t1.getName());
// System.out.println(t1.getAddress());
// but how to retrieve a list of meetings?

// meeting is not coming here.
// because session is closed.
// List<Course> courses = t1.getCourses();

// for(Course c : courses){
// System.out.println(c.getCourseName());
// }

// what if we retrieve data after session close?