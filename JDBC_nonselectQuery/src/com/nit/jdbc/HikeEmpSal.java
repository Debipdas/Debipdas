package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HikeEmpSal {
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con = null;
		Statement st = null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			float startRange=0.0f;
			float endRange=0.0f;
			int input=0;
			if(sc!=null) {
				System.out.println("Enter percentage to hike emp salary::");
				input=sc.nextInt();//gives percent to hike emp salary
				System.out.println("Enter start range of emp salary");
				startRange=sc.nextFloat();
				System.out.println("Enter end range of emp salary");
				endRange=sc.nextFloat();
				
			}//if
			//register jdbc driver by loading JDBC driver class
			  //Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system","manager");
			
			//create statement
			if(con!=null)
				st=con.createStatement();	
			
			//prepare SQL query
			  // UPDATE EMP SET SAL=SAL*2 WHERE SAL>=500 AND SAL<=800;
			String query = "UPDATE EMP SET SAL=SAL + (SAL*"+input+"/100) WHERE SAL>="+startRange+"AND SAL<="+endRange;
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
