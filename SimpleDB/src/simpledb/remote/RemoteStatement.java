package simpledb.remote;

import java.rmi.*;
import java.sql.Connection;


/**
 * The RMI remote interface corresponding to Statement.
 * The methods are identical to those of Statement, 
 * except that they throw RemoteExceptions instead of SQLExceptions.
 * @author Edward Sciore
 */
public interface RemoteStatement extends Remote {
   public RemoteResultSet executeQuery(String qry,int userCount) throws RemoteException;
   public int            executeUpdate(String cmd,int userCount) throws RemoteException;
}

