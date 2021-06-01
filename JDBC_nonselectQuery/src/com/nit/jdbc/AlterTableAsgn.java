package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterTableAsgn {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			//load JDBC driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system","manager");
			
			//create jdbc statement object
			if(con!=null)
				st=con.createStatement();
			
			//prepared SQL query
			// ALTER TABLE <TN> ADD <NEW COLUMN NAME> <DATATYPE>[SIZE];
			// ALTER TABLE STUDENT RENAME COLUMN SNAME TO STUDENTNAMES; 
			String query="ALTER TABLE STUD RENAME COLUMN SADD TO CITY";
			System.out.println(query);
			
			//send and execute SQL query
			int count=0;
			if(st!=null)
			count=st.executeUpdate(query);
			
			//process the result
			if(count==0)
				System.out.println("table alter successfully");
			else
				System.out.println("table is not alter");
			
			  
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
		
			
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
	}

}
