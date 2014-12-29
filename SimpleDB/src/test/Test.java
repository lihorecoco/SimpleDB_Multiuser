package test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import simpledb.remote.SimpleDriver;

/*
 * Test first runs server
 * Then create database for users that have even or odd user id
 * 
 * The basic idea is that 
 * 	- if a user has even user id , he/she will use even database that has been created for user that has even id.
 * 	- if a user has odd user id , he/she will use odd database that has been created for user that has odd id.
 * */
public class Test {

	
	public static void main(String[] args) {
		
		
		//Users
		String firstUser = "1";  //odd user
		String secondUser = "2"; //even user
		String thirdUser = "3";  //odd user
		
		//Before the test , server should be created and run then student databases should be created.
		RunServer server = new RunServer();
		CreateUserDB userDB = new CreateUserDB();
		
		server.run(); 	//First server runs
		
		userDB.createEvenUsersDatabase(); //create database for user that has even user id.
		userDB.createOddUsersDatabase(); //create database for user that has odd user id.
		
		//test to see whether database returns the result set which belongs to sepecific user properly or not.
		Test.getStudentTables(firstUser);
		Test.getStudentTables(secondUser);
		Test.getStudentTables(thirdUser);
		
		//test to see whether database returns the result set which belongs to sepecific user properly or not.
		Test.getStudentMajor(firstUser);
		Test.getStudentMajor(secondUser);
		Test.getStudentMajor(thirdUser);
		
		//test to see whether user can insert new data into its own table properly or not.
		Test.insertNewStudentIntoStudentTable(secondUser);
		
		//test to see whether inserted data have been inserted properly
		Test.getStudentTables(firstUser);
		Test.getStudentTables(secondUser);

	}
	
	public static void getStudentTables(String userID)
	{
		Connection conn = null;
		try {
			Driver d = new SimpleDriver();
			Properties prop = new Properties();
			prop.setProperty("UserID",userID);
			conn = d.connect("jdbc:simpledb://localhost", prop);

		
			Statement stmt = conn.createStatement();
			String qry = "select SName "
			           + "from  STUDENT ";
			ResultSet rs = stmt.executeQuery(qry);

			
			System.out.println("Name");
			while (rs.next()) {
				String sname = rs.getString("SName");
				System.out.println(sname);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void getStudentMajor(String userID)
	{
		Connection conn = null;
		try {
			Driver d = new SimpleDriver();
			Properties prop = new Properties();
			prop.setProperty("UserID",userID);
			conn = d.connect("jdbc:simpledb://localhost", prop);

			
			Statement stmt = conn.createStatement();
			String qry = "select SName, DName "
			           + "from DEPT, STUDENT "
			           + "where MajorId = DId";
			ResultSet rs = stmt.executeQuery(qry);

			
			System.out.println("Name\tMajor");
			while (rs.next()) {
				String sname = rs.getString("SName");
				String dname = rs.getString("DName");
				System.out.println(sname + "\t" + dname);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void insertNewStudentIntoStudentTable(String userID)
	{
		Connection conn = null;
		try {
			Driver d = new SimpleDriver();
			Properties prop = new Properties();
			prop.setProperty("UserID",userID);
			conn = d.connect("jdbc:simpledb://localhost", prop);

			
			Statement stmt = conn.createStatement();
			String s = "insert into STUDENT(SId, SName, MajorId, GradYear) values ";
			String[] studvals = {"(180201022, 'ismailkurar', 10, 2004)",
								 "(180201038, 'canberkdogan', 20, 2003)",
								 "(180201027,'alicansek',20,2002)",
								 "(180201045, 'cagdasduran', 10, 2005)"
								 };
			for (int i=0; i<studvals.length; i++)
				stmt.executeUpdate(s + studvals[i]);
			System.out.println("STUDENT records inserted.");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
