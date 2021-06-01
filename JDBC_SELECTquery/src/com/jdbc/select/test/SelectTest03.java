//SelectTest03.java
package com.jdbc.select.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/** java App to get Employee details based on given Employee name */

public class SelectTest03 {

	public static void main(String[] args) {
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//read inputs
			scn = new Scanner(System.in);
			String initChars = null;
			if(scn!=null) {
				System.out.println("Enetr initial character of employee name::");
				initChars = scn.next(); //gives s
			}
			
			//convert input values as required for the SQL query
			initChars ="'"+initChars+"%'"; //gives 's%'
			
			//register JDBC driver by loading JDBC driver class
				//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection
			 con = DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system", "manager");
			
			 //create statement object
			 if(con!=null)
				 st=con.createStatement();
			 
			 //prepare SQL query

		     // SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE 'A%';
			 String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+initChars;
			 System.out.println(query);
			 //send and execute SQL query in DB s/w
			 if(st!=null)
				 rs=st.executeQuery(query);
			 
			 //process the ResultSet object (0 or 1 record)
			 if (rs!=null) {
				 boolean flag=false;
				 while (rs.next()) {
					 flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));		
				}//while
				if(flag==false)
					System.out.println("No Records Found");
			}//if
			
		}//try
		catch(SQLException se) {
			if (se.getErrorCode()>=900&&se.getErrorCode()<=900) 
				System.out.println("invalid col names or table names or SQL keywords");
			se.printStackTrace();	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objects
			try {
				if(rs!=null)
					rs.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null)
					st.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(scn!=null)
					scn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main

}//class
