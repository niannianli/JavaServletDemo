import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.bean.Clazz2;
import com.spring.bean.User;
import com.spring.bean.AddressUser;
import com.spring.bean.Clazz;

public class Main1 {
	
	public static void main(String[] args) throws SQLException{
		
		//provided by Spring: only once please
		ApplicationContext context = new ClassPathXmlApplicationContext("springBeans.xml");		
		DataSource ds = (DataSource)context.getBean("datasource");	
		Connection conn = ds.getConnection();
		// conn will be created only once by Spring
			
		//  no use of new keyword, object obtained
		// and singleton object
		// only one, same,
	Clazz clazz = (Clazz)context.getBean("clazz");		
	clazz.callMe();	
	System.out.println(clazz.hashCode());
	
	Clazz clazz1 = (Clazz)context.getBean("clazz");	
	clazz1.callMe();
	System.out.println(clazz1.hashCode());
	
	// how to create multiple projects
	// scope="prototype"
	// by default: singleton
	System.out.println(clazz1.getName());
	
	// User is in Clazz and Clazz2, could we create one 
	// object only	
	Clazz2 clazz2 = (Clazz2)context.getBean("clazz2");	
	
User user = new User();
user.setPhoneNo("123");

clazz.setUser(user);
clazz2.setUser(user);
	
	// use spring, no need to write all the above
	// code to set User for every class
	// instead User bean can be shared with all the classes
	
	// we did not assign any user, null
	System.out.println(clazz.getUser());
	System.out.println(clazz2.getUser());
	// we use null to call null, NullPinterException
	System.out.println(clazz.getUser().getPhoneNo());
	System.out.println(clazz2.getUser().getPhoneNo());

	// if we do Address extends User
	// if you make changes to User class
	// Address will be affected also
	
	// but if we set User separately
	// User changes anyway
	// Address will not be affected
	
	// IS-A relationship:User is a address? no!
	
	/**
	Address a = new Address();
	a.setPhoneNo("89");
	a.setAddress("pa");
	*/
	
	// we do not do that
	// we use User as a variable
	
	// if we change User
	// Address is not affected
	// independent!
	// has-A: User has a address
	
	// depndendency injection: call spring container and get object
	// spring: provides object
	
	// maintain cleaner code, decoupling effective, not dependent on each other
	
	/**
	 * Dependency injection (DI) is a process whereby objects 
	 * define their dependencies, that is, 
	 * the other objects they work with, 
	 * only through constructor arguments, arguments to a factory method, 
	 * or properties that are set on the object 
	 * instance after it is constructed or 
	 * returned from a factory method.
	 * 
	 * same object injected into multiple beans
	 */
	
	User u = new User();
	u.setPhoneNo("89");
	
	AddressUser a = new AddressUser();
	a.setUser(u);
	a.setAddress("PA");
	
	// if multiple users try to call this calculate() method?
//	Main main = new Main();
//	main.calculate(10, 20);
	
	// then lots of objects will be created
	// slow, memeory crash, app crash
// better way?
	
	// we can config one object created only and provided to all users
	//thats what Spring do
	
	// in order to use call() method,
	// tons of users are created
	// then tons of operations are created
	// which is unnecessary

	// how to create one object only?
	// how to reduce object creation?
	// try static first
	
	// what if without static?
	// before runnning your app
	// all static objects are loaded already
	//which is not good.
	
	// better user configuration: to create the non-static object once
	// and thats all
	// if not null, use the object for all users	
	//Hibernate: one db connection only
		
	User user1 = new User();
	user1.call(12,34);
	
	User user2 = new User();
	user2.call(56,78);
		
	}

}

//use extends only with abstract class
class AbstractDao{
	
	public void insert(String table){
		
	}	
	
}

// all sub UserDao can extends this class
// and use all methods

//DataSource Interface: to connect db
// BasicDataSource
