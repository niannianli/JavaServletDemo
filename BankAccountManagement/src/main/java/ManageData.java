import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManageData {

	// resources folder: configurations, xml, property files inside

	// a jar file is implementation; you need to add this jar

	// but in maven, just add dependency in pom.xml

	public static void main(String[] args) throws Exception {

		// why we need this Class.forName: Driver class?
		// Class is a class
		// forName is a static method being called
		// so now, this Driver class is passed in
		// to be loaded

		connectToDB();
		retrieveDataWithProcedure();

		// only by doing this, the class is loaded
		// Class.forName("A");

		// execute("1234", 3456.789);
		// execute("12345678900000555555666666654333", 1000.123);
		// execute("1678900000555555666666654333", 1000.12);
		// execute("123456789", 10.10);
		// execute("987654321", 20.20);
		insertData("2231989", 40.00);
		insertDataWithBatch("1989223", 23.6);

		List<Student> studentList = retrieveAllData();

		for (Student s : studentList) {
			System.out.println(s.getId());
			System.out.println(s.getName());
		}

	}

	public static void connectToDB() {

	}

	public static void retrieveDataWithProcedure() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_data", "root", "love");
		Statement statement = conn.createStatement();

		// how many records are recorded
		statement.executeUpdate("insert into student values (30, 'john', 'wa', '3030')");
		// statement.execute();
		// works too, true or false
		statement.execute("select * from student");

		String input = "james";

		// a single result set object storing all datas
		ResultSet rs = statement.executeQuery("select * from student where name = 'james'");
		ResultSet rs1 = statement.executeQuery("select * from student where name = '" + input + "'");
		// docs.oracle.com
		// 3 rows: 0 1 2
		// 4 columns: 0 1 2 3
		// rs cursor always point to -1, so next() begins at 0 row
		// then you can retrieve 0th row, and get each column by identifier
		while (rs1.next()) {
			System.out.println(rs1.getInt("id"));
			System.out.println(rs1.getString("name"));
			System.out.println(".....................");
		}

		// sql:
		// compile
		// run
		// if you use Statement:
		// compile/run everytime
		// better:compile once
		// run multiple times
		// use PreparedStatement

		PreparedStatement ps = conn.prepareStatement("select * from student where name = ?");
		// index, value : safer this way
		ps.setString(1, input);

		ResultSet rs2 = ps.executeQuery();
		while (rs2.next()) {
			System.out.println(rs2.getInt("id"));
			System.out.println(rs2.getString("name"));
			System.out.println(".....................");
		}

		// web url-> server
		// open db->? sql injection->url?username=james

		// this never happen if you use prepared statement becasue you can use ?
		// then pass data
		// unlike statement pass data together with url, not safe

		// if pass wrong data, everything will be shown with Statement
		// if pass wrong data, nothing will be show with PreparedStatement
		// safer

		// if you use sql multiple times
		// compile once only
		// run multiple times

		// but Statement compile/ run multiple times

		// use procedure in java

		CallableStatement cs = conn.prepareCall("call execute()");
		ResultSet rscs = cs.executeQuery();

		while (rscs.next()) {
			System.out.println(rscs.getInt("id"));
			System.out.println(rscs.getString("name"));
			System.out.println(".....................");
		}

		conn.close();
	}

	public static void insertData(String accountNo, Double fund) {

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_data", "root", "love");

			// what might happen?
			// fund table got updated with amount of 1000.123
			// but error happens with accountNo

			// this should not happen, becasue the amount of fund
			// has no accordingly account_no

			// what should we do to avoid this?
			// to make sure both updated or neither is updated?

			// to maintain transaction?

			// auto_commit is default true, which is not good
			// we should set it as false, so only all transaction are completed
			// correctly
			// then we commit the data
			// if anything is wrong, no commit, no affecting db.

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into fund(amount) values(?)");
			ps.setDouble(1, fund);
			ps.executeUpdate();

			ps = conn.prepareStatement("insert into account(account_no) values(?)");
			ps.setString(1, accountNo);
			ps.executeUpdate();

			// everyhting is okay, then commits all
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				// if anything wrong, nothing commits
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertDataWithBatch(String accountNo, Double fund) {

		// want multiple insertions,
		// silly to insert multiple times using similar code
		// using for loop? too much repeating

		// batch operation is better
		// select
		// select
		// select

		// compile once
		// run 3 times

		// could we do better?

		// insert into account 2 times: compile once, run 2 times
		// insert into fund 3 times: compile once, run 3 times
		// using prepare statement
		// statement is worse

		// batch operation: compile once, run once
		// so much better!

		// jdbc batch insert
		// update batching

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_data", "root", "love");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into account(account_no) values(?)");
			ps.setString(1, accountNo);
			ps.addBatch();

			// above is not correct, batch only works for same table

			ps = conn.prepareStatement("insert into fund(amount) values(?)");
			ps.setDouble(1, fund);
			ps.addBatch();

			ps.setDouble(1, 90.10);
			ps.addBatch();

			// ps = conn.prepareStatement("insert into account(account_no)
			// values(?)");
			// ps.setString(1, accountNo);
			// ps.addBatch();

			ps.executeBatch();

			// everyhting is okay, then commits all
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				// if anything wrong, nothing commits
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// resuletset is not avaialble, after connection closed.
	// so we are not returning ResultSet, we are returning a list of Student
	// public static ResultSet execute(){
	public static List<Student> retrieveAllData() {

		Connection conn = null;

		try {

			List<Student> list = new ArrayList<Student>();

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_data", "root", "love");

			PreparedStatement ps = conn.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student student = new Student();

				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAddress(rs.getString("address"));
				student.setPhoneNo(rs.getString("phoneNo"));

				list.add(student);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ORM: object relational mapping
	// bean pojo class: table
	// variables: columns

	// easily use classes to map to a new db
	// just change the connection to db

	// then whole java code can be applied to new db

	// hibernate will help you generate all the tables to new db
	// through pojo classes

	// no manual work,
	// just run project,
	// hibernate will create all tables in new db
}


class A {
	static {
		System.out.println("static block");
	}
}

// update student set name = 'tom' where name = 'james';

// procedure:
// delimiter //
// create procedure execute()
// begin
// select * from java_data.student;
// end //

// delimiter ;
// call execute();

// JDBC: transaction
// ACID

// like a bank transaction?
// both affected
// or neither is changed
// to maintain balance

// in db:
// 2 tables

// 2 tables both changed
// or neither is changed
// maintain data correctly

// batch operation:
// if use jdbc, sql only,
// what if thousands of data handled?

// select * from student
// thousands of records
// how could we retrieve/print all the records?
// use result set?
// but result set only works before conn.close();
// if closed connection, result set could not be retrieved