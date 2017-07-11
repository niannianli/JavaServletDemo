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

import com.spring.dao.StudentDao;
import com.spring.bean.Address;
import com.spring.bean.Employee;
import com.spring.bean.Operation;
import com.spring.bean.Student;

public class Main {
	
	public static void main(String[] args) throws Exception{
		// no need to create object using new keyword
		// just define bean class in spring
		// all done
		// call spring configuration to use all the singleton beans/objects
		
		
		// Util class can serve as Spring
		// to create one object only
		
		//inject one bean to another
		
	//	Main m = new Main();	
		// even better, we can add studentdao in spring 
//StudentDao sd = new StudentDao();
		ApplicationContext context = new ClassPathXmlApplicationContext("springBeans.xml");	
		StudentDao sd = (StudentDao)context.getBean("studentDao");
		
		List<Student> list =  (List<Student>)sd.selectAll();
		
		for(Student s : list){
			System.out.println(s.getName());
		}
		
		// differnt objects
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		
		// same object, by deafult, always singleton
		// if set prototype: then differnt objects
		Employee e3 = (Employee)context.getBean("employee");
		Employee e4 = (Employee)context.getBean("employee");
		
		System.out.println(e1.hashCode());
		System.out.println(e2.hashCode());
		System.out.println(e3.hashCode());
		System.out.println(e4.hashCode());
	
	    Operation operation = (Operation)context.getBean("operation");
		Operation operation1 = (Operation)context.getBean("operation");
		Employee employee = (Employee)context.getBean("employee");
		
		System.out.println(operation.getSign());
		System.out.println(employee.getEmpName());
	    System.out.println(employee.getAddress().getStreet());
		
		// class type default null
		// non-primitive type data: int: 0
	//	Integer i = null;
	//	System.out.println(i);
		int i = 0;
		double d = 0;
		float f = 0;
		long l = 0;
		
		System.out.println(i);
		System.out.println(d);
		System.out.println(f);
		System.out.println(l);
		
		System.out.println(operation.hashCode());
		System.out.println(operation1.hashCode());
		
		// == always check memory
		// equals check value only
		System.out.println(operation == operation1);
				
		System.out.println(operation.calculate(23, 32));
		System.out.println(operation1.calculate(23, 32));

Address address = new Address();
Employee e7 = new Employee();
Employee e8 = new Employee();
		
//assign same addresss to 2 employees
		e7.setAddress(address);
		e8.setAddress(address);		
	}
	
}

// Spring convert property tag name, Spring use setName() to set the value
// now you can get value in java

// dependency injection:
// insert value to object

// 3 developers:
//1, create/maintain login
//2, user status
//3, check message/share mesage among users

// now we want to change login page
//before: username/password
// now: need to add capchar: to confirm you are not robot

// it should not affect other modules
// how to achieve that?

// class UserDetails
//username
//password
//chap

//class User
//name address telephone

//2 tables? we can do this.
// it works.
// but we can do better

// we inject User to other classes
// as variable, so only User 
// can change
// but other classes are not affected

// class UserDetails
// username
//password
//chap
//User user

// if you add dob in User
// not affect UserDetails

// you can extends class
// but it is not good
// changes will affect each other

// business logic do not change
// service()

//changes in servlet not affect service()

//if not using SPring:
// new an address
// add to Employee

// if use jdbc
// have to connect to db again and again
// hibernate better: once for all

// if use spring
// one bean, thats all







