package simpledb.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

/**
 * The RMI server-side implementation of RemoteDriver.
 * @author Edward Sciore
 */
@SuppressWarnings("serial")
public class RemoteDriverImpl extends UnicastRemoteObject implements RemoteDriver {
   public RemoteDriverImpl() throws RemoteException {
   }
   
   /**
    * Creates a new RemoteConnectionImpl object and 
    * returns it.
    * @see simpledb.remote.RemoteDriver#connect()
    */
   public RemoteConnection connect(Properties prop) throws RemoteException {
      return new RemoteConnectionImpl(prop);
   }
}

