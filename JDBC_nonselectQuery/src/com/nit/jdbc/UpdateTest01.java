package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest01 {

	public static void main(String[] args) {

            Scanner sc = null;
            Connection con = null;
            Statement st=null;
            try {
            	//read inputs
            	sc = new Scanner(System.in);
            	float hike_percent=0.0f;
            	String desg1=null,desg2=null,desg3=null;
            	
            	if(sc!=null) {
            		System.out.println("employee desg1::");
            		desg1=sc.next().toUpperCase(); //gives CLERK
            		System.out.println("employee desg2::");
            		desg2=sc.next().toUpperCase(); //gives MANAGER
            		System.out.println("employee desg3::");
            		desg3=sc.next().toUpperCase(); // gives ANALYST
            		System.out.println("salary hike percentage::");
            		hike_percent=sc.nextFloat();
            	}
            	//convert input values as required for the SQL query
            	desg1="'"+desg1+"'";  //gives 'CLERK'
            	desg2="'"+desg2+"'";  //gives  'MANAGER'
            	desg3="'"+desg3+"'";  //gives 'ANALYST'
            	
            	//register JDBC driver by loading JDBC driver class
            	   //Class.forName("oracle.jdbc.driver.OracleDriver");
            	
            	//establish the connection
            	con=DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system","manager");
            	
            	//create Statement object
            	if(con!=null)
            		st=con.createStatement();
            	
            	//prepare SQL query
            	   //update emp set sal=sal+(sal*10/100) where job in ('CLERK','MANAGER','ANALYST');
            	String query = "update emp set sal=sal+(sal*"+hike_percent/100+") where job in ("+desg1+","+desg2+","+desg3+")";
            	System.out.println(query);
            	
            	//send and execute SQL query in db s/w
            	int count=0;
            	if(st!=null)
            		count = st.executeUpdate(query);
            	System.out.println("updated successfully");
            	
            	//process the result
            	if(count==0)
            		System.out.println("No records found for updation");
            	else
            		System.out.println("No. of records that are effected::"+count);
            
            }//try
            catch(SQLException se) {
				if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
					System.out.println("Invalid col names or table names or SQL keywords");
				else if(se.getErrorCode()==12899)
					System.out.println("do not insert more than col size data to sname,city cols");
				se.printStackTrace();
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(st!=null)
					st.close();
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
	
	}

}
