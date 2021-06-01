package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateAsgn01 {
public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			//read inputs
			sc=new Scanner(System.in);
			String city1=null, city2=null, city3=null;
			float marks=0.0f;
			
			if(sc!=null) {
				System.out.println("Enter percentage of marks::");
				marks=sc.nextFloat(); //gives percentage of marks
				System.out.println("Enter City1::");
				city1=sc.next().toUpperCase(); //gives city1
				System.out.println("Enter City2::");
				city2=sc.next().toUpperCase(); //gives city2
				System.out.println("Enter City3::");
				city3=sc.next().toUpperCase(); //gives city3
				
				
			}//if
			
			//convert input values as required for the SQL query
			
			city1="'"+city1+"'"; //gives 'city1'
			city2="'"+city2+"'"; //gives 'city1'
			city3="'"+city3+"'"; //gives 'city1'
			
			//register jdbc driver by loading JDBC driver class
			  //Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system","manager");
			
			//create statement
			if(con!=null)
				st=con.createStatement();	
			
			//prepare SQL query
			  // UPDATE STUDENT SET AVG = AVG+5 WHERE CITY IN('HYD','PUNE','MUMBAI');
			String query = "UPDATE STUDENT SET AVG = AVG + "+marks+" WHERE CITY IN("+city1+","+city2+","+city3+")";
		    System.out.println(query);
		   
		    
		  //send and execute SQL query in DB s/w
			int count = 0;
			if(st!=null) 
				 count=st.executeUpdate(query);
			 System.out.println("successfully updated");
			
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
