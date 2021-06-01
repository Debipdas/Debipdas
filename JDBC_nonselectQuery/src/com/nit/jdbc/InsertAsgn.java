package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertAsgn {

	public static void main(String[] args) {
		 Scanner sc = null;
         Connection con = null;
         Statement st=null;
         try {
         	//read inputs
         	sc = new Scanner(System.in);
            int eno=0;
         	String ename=null,job=null;
         	float sal=0.0f;
         	
         	if(sc!=null) {
         		System.out.println("enter employee number::");
         		eno=sc.nextInt(); //gives 105
         		System.out.println("enter employee name::");
         		ename=sc.next().toUpperCase(); //gives sona
         		System.out.println("enter employee job::");
         		job=sc.next().toUpperCase(); //gives analyst
         		System.out.println("enter employee salary::");
         		sal=sc.nextFloat(); //gives 73.44
         	}
         	//convert input values as required for the SQL query
         	ename="'"+ename+"'"; //gives 'sona'
         	job="'"+job+"'"; //gives 'analyst'
         	
         	//register JDBC driver by loading JDBC driver class
         	   //Class.forName("oracle.jdbc.driver.OracleDriver");
         	
         	//establish the connection
         	con=DriverManager.getConnection("jdbc:oracle:thin:@Sona:1521:xe","system","manager");
         	
         	//create Statement object
         	if(con!=null)
         		st=con.createStatement();
         	
         	//prepare SQL query
         	   //INSERT INTO EMP(EMPNO,ENAME,JOB,SAL)VALUES(1003,'SAURABH','ANALYST',2700);
         	String query = "INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES("+eno+","+ename+","+job+","+sal+")";
         	System.out.println(query);
         	
         	//send and execute SQL query in db s/w
         	int count=0;
         	if(st!=null)
         		count = st.executeUpdate(query);
         	
         	
         	//process the result
         	if(count==0)
         		System.out.println("Record not inserted");
         	else
         		System.out.println("Record inserted");
         
         }//try
         catch(SQLException se) {
        	 	if (se.getErrorCode()==1)
        	 		System.out.println("Duplicates can not inserted to PK column");
        	   if(se.getErrorCode()==1400)
        		   System.out.println("Null can not inserted to PK column");
				if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
					System.out.println("Invalid col names or table names or SQL keywords");
				else if(se.getErrorCode()==12899)
					System.out.println("do not insert more than col size data to sname,city cols");
				
				System.out.println("problem in recodr insertion......");
				
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

