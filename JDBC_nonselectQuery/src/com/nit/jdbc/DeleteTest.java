package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//read inputs
			sc= new Scanner(System.in);
			String city=null;
			if(sc!=null) {
				System.out.println("Enter City name:: ");
				city = sc.next().toUpperCase(); //gives city
			}
			city ="'"+city+"'" ; //gives 'city'
			
			//register JDBC driver by loading JDBC driver class
			  //Class.forName("jdbc:oracle:driver:OracleDriver");
			
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system","manager");
			
			//create statement object
			if(con!=null)
				st= con.createStatement();
			
			//prepare SQL query
			   //DELETE FROM STUDENT WHERE CITY='MUMBAI';
			String query="DELETE FROM STUDENT WHERE CITY="+city;
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
		
	}//main
}//class


