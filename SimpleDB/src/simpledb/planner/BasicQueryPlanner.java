package simpledb.planner;

import simpledb.tx.Transaction;
import simpledb.query.*;
import simpledb.parse.*;
import simpledb.server.SimpleDB;

import java.util.*;

/**
 * The simplest, most naive query planner possible.
 * @author Edward Sciore
 */
public class BasicQueryPlanner implements QueryPlanner {
   
   /**
    * Creates a query plan as follows.  It first takes
    * the product of all tables and views; it then selects on the predicate;
    * and finally it projects on the field list. 
    */
   public Plan createPlan(QueryData data, Transaction tx ,Properties prop) {
      //Step 1: Create a plan for each mentioned table or view
	   int id=Integer.parseInt(prop.getProperty("UserID")); 
	   
      List<Plan> plans = new ArrayList<Plan>();
      for (String tblname : data.tables()) {
    	  tblname=ConvertTableName(tblname, id);
         String viewdef = SimpleDB.mdMgr().getViewDef(tblname, tx);
         if (viewdef != null)
            plans.add(SimpleDB.planner().createQueryPlan(viewdef, tx ,prop));
         else
            plans.add(new TablePlan(tblname, tx));
      }
      
      //Step 2: Create the product of all table plans
      Plan p = plans.remove(0);
      for (Plan nextplan : plans)
         p = new ProductPlan(p, nextplan);
      
      //Step 3: Add a selection plan for the predicate
      p = new SelectPlan(p, data.pred());
      
      //Step 4: Project on the field names
      p = new ProjectPlan(p, data.fields());
      return p;
   }
   
   public String ConvertTableName(String tblname,int id)
   {
	   
	   if(id%2==0)
		   return "S2"+tblname;
	   else
		   return "S1"+tblname;
   }
   
}
