package simpledb.planner;

import java.util.Iterator;
import java.util.Properties;

import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;
import simpledb.parse.*;
import simpledb.query.*;

/**
 * The basic planner for SQL update statements.
 * @author sciore
 */
public class BasicUpdatePlanner implements UpdatePlanner {
	   
	   public int executeDelete(DeleteData data, Transaction tx,Properties prop) {
		  int id=Integer.parseInt(prop.getProperty("UserID"));
	      Plan p = new TablePlan(ConvertTableName(data.tableName(), id), tx);
	      p = new SelectPlan(p, data.pred());
	      UpdateScan us = (UpdateScan) p.open();
	      int count = 0;
	      while(us.next()) {
	         us.delete();
	         count++;
	      }
	      us.close();
	      return count;
	   }
	   
	   public int executeModify(ModifyData data, Transaction tx,Properties prop) {
	      int id=Integer.parseInt(prop.getProperty("UserID"));
	      Plan p = new TablePlan(ConvertTableName(data.tableName(), id), tx);
	      p = new SelectPlan(p, data.pred());
	      UpdateScan us = (UpdateScan) p.open();
	      int count = 0;
	      while(us.next()) {
	         Constant val = data.newValue().evaluate(us);
	         us.setVal(data.targetField(), val);
	         count++;
	      }
	      us.close();
	      return count;
	   }
	   
	   public int executeInsert(InsertData data, Transaction tx,Properties prop) {
		  int id=Integer.parseInt(prop.getProperty("UserID"));
	      Plan p = new TablePlan(ConvertTableName(data.tableName(), id), tx);
	      UpdateScan us = (UpdateScan) p.open();
	      us.insert();
	      Iterator<Constant> iter = data.vals().iterator();
	      for (String fldname : data.fields()) {
	         Constant val = iter.next();
	         us.setVal(fldname, val);
	      }
	      us.close();
	      return 1;
	   }
	   public int executeCreateTable(CreateTableData data, Transaction tx,Properties prop) {
		  int id=Integer.parseInt(prop.getProperty("UserID"));
	      SimpleDB.mdMgr().createTable(ConvertTableName(data.tableName(), id), data.newSchema(), tx);
	      return 0;
	   }
	   public int executeCreateView(CreateViewData data, Transaction tx,Properties prop) {
		  int id=Integer.parseInt(prop.getProperty("UserID"));
	      SimpleDB.mdMgr().createView(ConvertTableName(data.viewName(), id), data.viewDef(), tx);
	      return 0;
	   }
	   public int executeCreateIndex(CreateIndexData data, Transaction tx,Properties prop) {
		  int id=Integer.parseInt(prop.getProperty("UserID"));
	      SimpleDB.mdMgr().createIndex(data.indexName(), ConvertTableName(data.indexName(), id), data.fieldName(), tx);
	      return 0;  
	   }
	   
	   public String ConvertTableName(String tblname,int id)
	   {
		   
		   if(id%2==0)
			   return "S2"+tblname;
		   else
			   return "S1"+tblname;
	   }
}
