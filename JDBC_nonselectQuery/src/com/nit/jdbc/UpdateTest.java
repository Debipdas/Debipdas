//UpdateTest.java
package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			//read inputs
			sc=new Scanner(System.in);
			String newCity=null, newName=null;
			float newAvg=0.0f;
			int no=0;
			
			if(sc!=null) {
				System.out.println("Enter new name of student::");
				newName=sc.nextLine().toUpperCase(); //gives allu arjun
				System.out.println("Enter new address of student::");
				newCity=sc.nextLine().toUpperCase(); //gives navi mumbai
				System.out.println("Enter new avg of student::");
				newAvg=sc.nextFloat(); //gives 89.99
				System.out.println("Enter new sno of student::");
				no=sc.nextInt(); //gives 103
				
			}//if
			
			//convert input valuees as required for the SQL query
			newName="'"+newName+"'"; //gives 'anil rao'
			newCity="'"+newCity+"'"; //gives 'navi mumbai'
			
			//register jdbc driver by loading JDBC driver class
			  //Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system","manager");
			
			//create statement
			if(con!=null)
				st=con.createStatement();	
			
			//prepare SQL query
			  // UPDATE STUDENT SET SNAME='ALLU ARJUN', CITY='NAVI MUMBAI',AVG=90.78 WHERE SID=103;
			String query = "UPDATE STUDENT SET SNAME="+newName+", CITY="+newCity+",AVG="+newAvg+" WHERE SID="+no;
		    System.out.println(query);
		    
		  //send and execute SQL query in DB s/w
			int count = 0;
			if(st!=null) 
				 count=st.executeUpdate(query);
			
			//process the result
			if(count==0)
				System.out.println("No Records found to delete");
			else
				System.out.println("NO. of records that are effected::"+count);
				
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

