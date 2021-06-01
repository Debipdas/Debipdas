package com.partice.jdbc;
/* write a jdbc app to get highest sal record from emp db table */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SelectTest06 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			
			//load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con= DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system","manager");
			
			//create JDBC statement object
			if(con!=null)
				st = con.createStatement();
			
			//prepare SQL query
			 //SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP);
			String query = "\r\n"
					+ "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP);"; 
			System.out.println(query);
			
			//send and execute the SQL query
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the ResultSet (0 or more record)
			if(rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}//while
				
				if(flag==false)
					System.out.println("Records Not Found");
				
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
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
			
			
		}//finally
	}//main

}//class


