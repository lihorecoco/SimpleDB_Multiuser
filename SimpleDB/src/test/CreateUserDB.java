package test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import simpledb.remote.SimpleDriver;

public class CreateUserDB {

	
	public CreateUserDB(){
		
	}
	
	public void createEvenUsersDatabase()
	{
		
		Connection conn = null;
		Properties prop=new Properties();
		try {
			Driver d = new SimpleDriver();
			prop.setProperty("UserID","2");
			conn = d.connect("jdbc:simpledb://localhost", prop);
			Statement stmt = conn.createStatement();

			String s = "create table STUDENT(SId int, SName varchar(10), MajorId int, GradYear int)";
			stmt.executeUpdate(s);
			System.out.println("Table STUDENT created for EVEN USERS.");

			s = "insert into STUDENT(SId, SName, MajorId, GradYear) values ";
			String[] studvals = {"(12, 'joe_even', 10, 2004)",
								 "(23, 'amy_even', 20, 2004)",
								 "(3, 'max_even', 10, 2005)",
								 "(4, 'sue_even', 20, 2005)",
								 "(5, 'bob_even', 30, 2003)",
								 "(6, 'kim_even', 20, 2001)",
								 "(7, 'art_even', 30, 2004)",
								 "(8, 'pat_even', 20, 2001)",
								 "(9, 'lee_even', 10, 2004)"};
			for (int i=0; i<studvals.length; i++)
				stmt.executeUpdate(s + studvals[i]);
			System.out.println("STUDENT records inserted.");

			s = "create table DEPT(DId int, DName varchar(8))";
			stmt.executeUpdate(s);
			System.out.println("Table DEPT created.");

			s = "insert into DEPT(DId, DName) values ";
			String[] deptvals = {"(10, 'compsci_even')",
								 "(20, 'math_even')",
								 "(30, 'drama_even')"};
			for (int i=0; i<deptvals.length; i++)
				stmt.executeUpdate(s + deptvals[i]);
			System.out.println("DEPT records inserted.");

			s = "create table COURSE(CId int, Title varchar(20), DeptId int)";
			stmt.executeUpdate(s);
			System.out.println("Table COURSE created.");

			s = "insert into COURSE(CId, Title, DeptId) values ";
			String[] coursevals = {"(12, 'db systems_even', 10)",
								   "(22, 'compilers_even', 10)",
								   "(32, 'calculus_even', 20)",
								   "(42, 'algebra_even', 20)",
								   "(52, 'acting_even', 30)",
								   "(62, 'elocution_even', 30)"};
			for (int i=0; i<coursevals.length; i++)
				stmt.executeUpdate(s + coursevals[i]);
			System.out.println("COURSE records inserted.");

			s = "create table SECTION(SectId int, CourseId int, Prof varchar(8), YearOffered int)";
			stmt.executeUpdate(s);
			System.out.println("Table SECTION created.");

			s = "insert into SECTION(SectId, CourseId, Prof, YearOffered) values ";
			String[] sectvals = {"(13, 12, 'turing_even', 2004)",
								 "(23, 12, 'turing_even', 2005)",
								 "(33, 32, 'newton_even', 2000)",
								 "(43, 32, 'einstein_even', 2001)",
								 "(53, 62, 'brando_even', 2001)"};
			for (int i=0; i<sectvals.length; i++)
				stmt.executeUpdate(s + sectvals[i]);
			System.out.println("SECTION records inserted.");

			s = "create table ENROLL(EId int, StudentId int, SectionId int, Grade varchar(2))";
			stmt.executeUpdate(s);
			System.out.println("Table ENROLL created.");

			s = "insert into ENROLL(EId, StudentId, SectionId, Grade) values ";
			String[] enrollvals = {"(14, 1, 13, 'A')",
								   "(24, 1, 43, 'C' )",
								   "(34, 2, 43, 'B+')",
								   "(44, 4, 33, 'B' )",
								   "(54, 4, 53, 'A' )",
								   "(64, 6, 53, 'A' )"};
			for (int i=0; i<enrollvals.length; i++)
				stmt.executeUpdate(s + enrollvals[i]);
			System.out.println("ENROLL records inserted.");

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
	
	public void createOddUsersDatabase()
	{
		
		Connection conn = null;
		Properties prop=new Properties();
		try {
			Driver d = new SimpleDriver();
			prop.setProperty("UserID","1");
			conn = d.connect("jdbc:simpledb://localhost", prop);
			Statement stmt = conn.createStatement();

			String s = "create table STUDENT(SId int, SName varchar(10), MajorId int, GradYear int)";
			stmt.executeUpdate(s);
			System.out.println("Table STUDENT created for ODD USERS.");

			s = "insert into STUDENT(SId, SName, MajorId, GradYear) values ";
			String[] studvals = {"(12, 'joe_odd', 10, 2004)",
								 "(23, 'amy_odd', 20, 2004)",
								 "(3, 'max_odd', 10, 2005)",
								 "(4, 'sue_odd', 20, 2005)",
								 "(5, 'bob_odd', 30, 2003)",
								 "(6, 'kim_odd', 20, 2001)",
								 "(7, 'art_odd', 30, 2004)",
								 "(8, 'pat_odd', 20, 2001)",
								 "(9, 'lee_odd', 10, 2004)"};
			for (int i=0; i<studvals.length; i++)
				stmt.executeUpdate(s + studvals[i]);
			System.out.println("STUDENT records inserted.");

			s = "create table DEPT(DId int, DName varchar(8))";
			stmt.executeUpdate(s);
			System.out.println("Table DEPT created.");

			s = "insert into DEPT(DId, DName) values ";
			String[] deptvals = {"(10, 'compsci_odd')",
								 "(20, 'math_odd')",
								 "(30, 'drama_odd')"};
			for (int i=0; i<deptvals.length; i++)
				stmt.executeUpdate(s + deptvals[i]);
			System.out.println("DEPT records inserted.");

			s = "create table COURSE(CId int, Title varchar(20), DeptId int)";
			stmt.executeUpdate(s);
			System.out.println("Table COURSE created.");

			s = "insert into COURSE(CId, Title, DeptId) values ";
			String[] coursevals = {"(12, 'db systems_odd', 10)",
								   "(22, 'compilers_odd', 10)",
								   "(32, 'calculus_odd', 20)",
								   "(42, 'algebra_odd', 20)",
								   "(52, 'acting_odd', 30)",
								   "(62, 'elocution_odd', 30)"};
			for (int i=0; i<coursevals.length; i++)
				stmt.executeUpdate(s + coursevals[i]);
			System.out.println("COURSE records inserted.");

			s = "create table SECTION(SectId int, CourseId int, Prof varchar(8), YearOffered int)";
			stmt.executeUpdate(s);
			System.out.println("Table SECTION created.");

			s = "insert into SECTION(SectId, CourseId, Prof, YearOffered) values ";
			String[] sectvals = {"(13, 12, 'turing_odd', 2004)",
								 "(23, 12, 'turing_odd', 2005)",
								 "(33, 32, 'newton_odd', 2000)",
								 "(43, 32, 'einstein_odd', 2001)",
								 "(53, 62, 'brando_odd', 2001)"};
			for (int i=0; i<sectvals.length; i++)
				stmt.executeUpdate(s + sectvals[i]);
			System.out.println("SECTION records inserted.");

			s = "create table ENROLL(EId int, StudentId int, SectionId int, Grade varchar(2))";
			stmt.executeUpdate(s);
			System.out.println("Table ENROLL created.");

			s = "insert into ENROLL(EId, StudentId, SectionId, Grade) values ";
			String[] enrollvals = {"(14, 1, 13, 'A')",
								   "(24, 1, 43, 'C' )",
								   "(34, 2, 43, 'B+')",
								   "(44, 4, 33, 'B' )",
								   "(54, 4, 53, 'A' )",
								   "(64, 6, 53, 'A' )"};
			for (int i=0; i<enrollvals.length; i++)
				stmt.executeUpdate(s + enrollvals[i]);
			System.out.println("ENROLL records inserted.");

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
