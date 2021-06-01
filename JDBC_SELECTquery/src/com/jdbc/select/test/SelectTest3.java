package com.jdbc.select.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {
	public static void main(String[] args) 
	{
		//read inputs from enduser
		Scanner sc = null;
		int Deptno=0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;


		try{
			sc = new Scanner(System.in);
		   if(sc!=null){
			System.out.print("Enter Deptno:::");
			Deptno=sc.nextInt();
		   }//if
			
			//load jdbc driver
			   //Class.forName("oracle.jdbc.driver.OracleDriver");

			//establish the connection
			 con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");


			//create statement object
			if(con!=null)
				 st = con.createStatement();

			//prepare SQL query
			   
			
			String query = "SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE DEPTNO IN("+Deptno+")ORDER BY JOB";
			
			System.out.println(query);

			//send and execute SQL query in DB s/w
			
			if(st!=null)
				rs = st.executeQuery(query);

			if(rs!=null){
				System.out.println("The Employee details are::");
				while(rs.next()!=false){
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));

				}//while
			}//if

		}//try
		catch(SQLException se){ //to handle known exception
			se.printStackTrace(); //gives details info about the raised exception
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){ 
				se.printStackTrace(); 
			}

			try{
				if(st!=null)
					st.close();
			}
			catch(SQLException se){ 
				se.printStackTrace(); 
			}

			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){ 
				se.printStackTrace(); 
			}

			try{
				if(sc!=null)
					sc.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}
		
	}//main
}//class

