package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAsgn {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			//load JDBC driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system","manager");
			
			//create jdbc statement object
			if(con!=null)
				st=con.createStatement();
			
			//prepare the SQL query
			//CREATE TABLE STUDENT (STID INT,SNAME CHAR(10),SFEE NUMBER(6,2)); 
			String  query = "CREATE TABLE STUD(SNO NUMBER(10), SNAME VARCHAR2(2),AVG NUMBER(6,2),PRIMARY KEY(SNO))";
			System.out.println(query);
			
			//send and execute Sql query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
		 	
         	//process the result
         	if(count==0)
         		System.out.println("TABLE IS CREATED");
         	else
         		System.out.println("TABLE IS NOT CREATED");
			
			
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
