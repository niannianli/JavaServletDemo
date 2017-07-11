package com.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.bean.Student;

//handles Student bean
public class StudentDao {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("springBeans.xml");
	// if use constructor autowired
	// define constructor here
	
//	public StudentDao(SessionFactory sessionFactory){
	//	this.sessionFactory = sessionFactory;
	//}
	
	//public SessionFactory getSessionFactory() {
	//	return sessionFactory;
	//}

	//public void setSessionFactory(SessionFactory sessionFactory) {
		//this.sessionFactory = sessionFactory;
	//}

// various data sources, but type is same
// autowire not working
//.xml <-->annoataion
public void insert(Student student) throws SQLException{
	
	// Driver
	// understand url
	// root
	//connect db
	//...
	
	// configure in spring, once for all
	
BasicDataSource dataSource = (BasicDataSource)context.getBean("dataSource");
Connection conn = dataSource.getConnection();
// now we can insert data
		
}

//you can use MySQL to search db
public List<Student> selectAll() throws Exception {

	BasicDataSource dataSource = (BasicDataSource)context.getBean("dataSource");
	Connection conn = dataSource.getConnection();
	
	PreparedStatement ps = conn.prepareStatement("select * from student");	
	ResultSet rs = ps.executeQuery();
	
	List<Student> list = new ArrayList<Student>();
	
	while(rs.next()){
		Student s = new Student();
		s.setId(rs.getInt("id"));
		s.setName(rs.getString("name"));
		list.add(s);
	}
	
	return list;
}

//with hibernate Criteria, no deal with db, hibernate do all these for you
public List<Student> selectAllWithCriteria() throws Exception {
	
	// no repeat this code, just bean inject into bean and use it	
	//you can do this, you can also inject sessionFactory to StudentDao class and use sessinFactory directly
SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
	Session session = sessionFactory.openSession();	
	
	//hibernate searchese the Student table for you
	Criteria criteria = session.createCriteria(Student.class);	
	List<Student> list = criteria.list();		
	session.close();		
	return list;		
}

}