package com.registration.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.registration.pojo.Employee;
import com.registration.pojo.User;
import com.registration.pojo.old_employee;
import com.registration.util.HibernateUtil;

public class HQL {
	
	public static void main(String[] args){
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
	
		String hql1 = "FROM User AS U";
		String hql2 = "FROM User U";
		String hql3 = "SELECT U.name FROM User U";
		String hql4 = "FROM User U WHERE U.id = 21";
		String hql5 = "FROM User U WHERE U.id > 10 ORDER BY U.id DESC";
		String hql6 = "FROM User U WHERE U.id > 10 ORDER BY U.id ASC";
		String hql7 = "FROM User U WHERE U.id > 10 ORDER BY U.name DESC, U.id ASC";
		String hql8 = "SELECT SUM(E.salary), E.firstName FROM Employee E GROUP BY E.firstName";
		
		Employee employee = new Employee();
		employee.setFirstName("Brian");
		
		// assign address to the user, this address never changed after we added 2 new addresses to the user
		employee.setLastName("Tilman");
		employee.setSalary(1000);
	
		//	user.setPassword("TestPassword");
		session.save(employee);
		
		String hql9 = "FROM Employee E WHERE E.id = :employee_id";
		Query query9 = session.createQuery(hql9);
		
		query9.setParameter("employee_id",1);
		List results9 = query9.list();
		
		// protect data from sql
		String hql10 = "UPDATE Employee set salary = :salary WHERE id = :employee_id";
	    Query query10 = session.createQuery(hql10);
	
	// only set data here
	query10.setParameter("salary", 1000);
	query10.setParameter("employee_id", 1);
	
	int result10 = query10.executeUpdate();
	System.out.println("Rows affected: " + result10);
		
	String hql11 = "DELETE FROM Employee WHERE id = :employee_id";
Query query11 = session.createQuery(hql11);
query11.setParameter("employee_id", 1);
int result11 = query11.executeUpdate();
System.out.println("Rows affected: " + result11);

//session.save: insert new record to db withouth INSERTION
old_employee old_employee = new old_employee();
old_employee.setFirstName("Brian");
old_employee.setLastName("Michael");
old_employee.setSalary(5000);
session.save(old_employee);

// or you can use INSERT statement, but no explicit data using HQL
String hql12 = "INSERT INTO Employee(firstName, lastName, salary) SELECT firstName, lastName, salary FROM old_employee";
Query query12 = session.createQuery(hql12);
int result12 = query12.executeUpdate();
System.out.println("Rows affected: " + result12);

String hql13 = "SELECT count(distinct E.firstName) FROM Employee E";
Query query13 = session.createQuery(hql13);
List results13 = query13.list();

String hql14 = "FROM Employee";
Query query14 = session.createQuery(hql14);
query14.setFirstResult(1);// from row 1
query14.setMaxResults(10);// 10 rows only
List results14 = query14.list();
		
		Query query1 = session.createQuery(hql1);
		List<User> userList = query1.list();

		for (User u : userList) {
			System.out.println(u.getName() +":" + u.getAddress());
		}

		session.getTransaction().commit();
		session.close();
	}

}
