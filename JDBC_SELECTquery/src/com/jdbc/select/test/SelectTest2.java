package com.jdbc.select.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2 {
	public static void main(String[] args) 
	{
		//read inputs from enduser
		Scanner sc = null;
		String desg1 = null, desg2 = null,desg3 = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;


		try{
			sc = new Scanner(System.in);
		   if(sc!=null){
			System.out.println("enter desg1::");
			desg1 = sc.next().toUpperCase(); //gives CLERK

			System.out.println("enter desg2::");
			desg2 = sc.next().toUpperCase();  // gives MANAGER

			System.out.println("enter desg3::");
			desg3 = sc.next().toUpperCase();  // gives SALESMAN
		   }//if

			//conver input values as required for the SQL query
			desg1="'"+desg1+"'"; //gives 'CLERK'
			desg2="'"+desg2+"'"; //gives 'MANAGER'
			desg3="'"+desg3+"'"; //gives 'SALESMAN'

			//load jdbc driver
			   //Class.forName("oracle.jdbc.driver.OracleDriver");

			//establish the connection
			 con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");


			//create statement object
			if(con!=null)
				 st = con.createStatement();

			//prepare SQL query
			    // SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE JOB IN('CLERK','MANAGER','SALESMAN')ORDER BY JOB;
			String query = "SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE JOB IN("+desg1+","+desg2+","+desg3+")ORDER BY JOB";
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
		/*finally{
			//close jdbc objs and stream objs
			try{
				if(rs!=null&&st!=null&&con!=null&&sc!=nill){
					rs.close();
					st.close();
					con.close();   //Bad code bcoz of while closing one object
					sc.close();    //if any exception raised the controll goes to catch(-)
				}//if              //and does not give fair chance for the objs in try block to get closed
			}//try
			catch(SQLException se){ 
				se.printStackTrace(); 
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}//finally */
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