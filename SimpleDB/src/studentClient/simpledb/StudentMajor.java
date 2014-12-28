package studentClient.simpledb;

import java.sql.*;

import simpledb.remote.SimpleDriver;
import simpledb.remote.SimpleStatement;

public class StudentMajor {
    public static void main(String[] args) {
		Connection conn = null;
		try {
			
			int user = 1;
			// Step 1: connect to database server
			Driver d = new SimpleDriver();
			conn = d.connect("jdbc:simpledb://localhost", null);
			
			// Step 2: execute the query
			SimpleStatement stmt = (SimpleStatement) conn.createStatement();
			String qry = "select SNAME, DNAME "
			           + "from DEPT, STUDENT "
			           + "where MajorId = DId";
			ResultSet rs = stmt.executeQuery(qry,user);
			ResultSetMetaData md = rs.getMetaData();
			// Step 3: loop through the result set
			System.out.println("Name\tMajor");
			while (rs.next()) {
				String sname = rs.getString(md.getColumnName(1));
				String dname = rs.getString(md.getColumnName(2));
				System.out.println(sname + "\t" + dname);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// Step 4: close the connection
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
