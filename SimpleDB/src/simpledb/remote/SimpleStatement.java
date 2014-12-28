package simpledb.remote;

import java.sql.*;

/**
 * An adapter class that wraps RemoteStatement.
 * Its methods do nothing except transform RemoteExceptions
 * into SQLExceptions.
 * @author Edward Sciore
 */
public class SimpleStatement extends StatementAdapter {
   private RemoteStatement rstmt;
   
   public SimpleStatement(RemoteStatement s) {
      rstmt = s;
   }
   
   public ResultSet executeQuery(String qry,int userCount) throws SQLException {
      try {
         RemoteResultSet rrs = rstmt.executeQuery(qry,userCount);
         return new SimpleResultSet(rrs);
      }
      catch(Exception e) {
         throw new SQLException(e);
      }
   }
   
   public int executeUpdate(String cmd,int userCount) throws SQLException {
      try {
         return rstmt.executeUpdate(cmd,userCount);
      }
      catch(Exception e) {
         throw new SQLException(e);
      }
   }

@Override
public ResultSet executeQuery(String sql) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}
}

