package com.jdbc.preparest.test;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest2 {
   private  static final String CUSTOMER_INSERT_QUERY="INSERT INTO CUSTOMER VALUES(?,?,?,?)"; 

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		  try {
			  //read inputs
			  sc=new Scanner(System.in);
			  int count=0;
			  if(sc!=null) {
				  System.out.println("Enter Customer count::");
				  count=sc.nextInt();
			  }
			  //register JDBC driver
			    //Class.forName("oracle.jdbc.driver.OracleDriver");

			  //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");
			
			  //create PreparedStatement object having pre-compiled SQL query
			  if(con!=null)
			     ps=con.prepareStatement(CUSTOMER_INSERT_QUERY);
			   //read input values from enduser ,set them to query param values and execute the pre-compiled
			   //SQL query for multiple times
			   if(ps!=null && sc!=null) {
				   for(int i=1;i<=count;++i) {
					   //read each student input values
					   System.out.println("Enter "+i+" Customer details");
					   System.out.print("Enter Customer slno   ::");
					   int sno=sc.nextInt();
					   System.out.print("Enter Customer  name  ::");
					   String name=sc.next();
					   System.out.print("Enter Customer address::");
					   String addrs=sc.next();
					   System.out.print("Enter Customer mobno  ::");
					   long mobno=sc.nextLong();
					   //set each student details as pre-compiled SQL query params
					   ps.setInt(1, sno); ps.setString(2, name); ps.setString(3, addrs); ps.setFloat(4, mobno);
					   //execute pre-compiled SQL query each time
					   int result=ps.executeUpdate();
					   //process execution result of pre-compiled-SQL query
					   if(result==0)
						   System.out.println(i+" Customer details not inserted");
					   else
						   System.out.println(i+" Customer details are inserted");
				   }//for
			   }//if

		  }//try
		  catch(SQLException se) {
			  se.printStackTrace();
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  finally {
			  //close jdbc objs
			  try {
				  if(ps!=null)
					  ps.close();
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
			  try {
				  if(con!=null)
					  con.close();
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
			  try {
				  if(sc!=null)
					  sc.close();
			  }
			  catch(Exception e) {
				  e.printStackTrace();
			  }
		  }//finally

	}//main
}//class