package test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import simpledb.remote.RemoteDriver;
import simpledb.remote.RemoteDriverImpl;
import simpledb.server.SimpleDB;

public class RunServer {
	
	
	public void run() 
	{
		try {
			// configure and initialize the database
		      SimpleDB.init("simpledb");
		      
		      // create a registry specific for the server on the default port
		      Registry reg = LocateRegistry.createRegistry(1099);
		      
		      // and post the server entry in it
		      RemoteDriver d = new RemoteDriverImpl();
		      reg.rebind("simpledb", d);
		      
		      System.out.println("database server ready");
		} catch (Exception e) {
			 System.out.println("SERVER CREATION FAIL");
		}
		
	}
	

}
