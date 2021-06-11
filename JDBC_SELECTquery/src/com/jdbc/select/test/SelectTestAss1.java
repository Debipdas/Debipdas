package com.jdbc.select.test;

import java.sql.Connection;

//JDBC APP TO GET EMPLOYEE DETAILS WHOSE HAVING "Nth" HEIGHT SALARY..
//TEAM-JAVA

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTestAss1{

	public static void main(String[] args) {

		Scanner scn=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		
		try {
			scn=new Scanner(System.in);
			int n=0;
			if(scn!=null) {//Input Reader
				
				System.out.print("Enter N value for checking height salary::");
			     n=scn.nextInt();
			}	
			//class load
			//Class.forName("oracle.jdbc.driver.OracleDriver");  (Automatically stored)
			
			//Established
			con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
			
			if(con!=null)
				//create statement
				st=con.createStatement();
			//prepare SQL query
			// SELECT ename,sal from Emp e1 where 1-1= (SELECT COUNT(DISTINCT sal)from Emp e2 where e2.sal > e1.sal);
			String query="SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP E1 WHERE "+n+"-1=(SELECT COUNT (DISTINCT SAL) FROM EMP E2 WHERE E2.SAL > E1.SAL)";
			System.out.println(query);
			
			//send execute SQL query
				if(st!=null)
					rs=st.executeQuery(query);
				System.out.println("THE EMPLOYEE DETAILS WHOSE HAVING "+n+" HEIGHT SALARY");
				
				//process the ResultSet
				if(rs!=null) {
					 boolean flag = false;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getInt(4));
				}//while
				if(flag==false)
				System.err.println("Records not found");
				}//if
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(scn!=null)
					scn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}

}