package com.jdbc.select.test;

//JDBC APP THAT GETS STUDENT DETAILS FROM STUDENT DB TABLE BASED ON GIVEN CITY
//AUTHOR-TEAM-JAVA
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest4 {// Student table

	public static void main(String[] args) {

		Scanner scn=null;//Read inputs
		
		String City1=null, City2=null ,City3=null;
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			scn=new Scanner(System.in);
			if(scn!=null) {
				System.out.print("Enter 1st City::");
				City1=scn.next().toUpperCase();
				System.out.print("Enter 2nd City::");
				City2=scn.next().toUpperCase();
				System.out.print("Enter 3rd City::");
				City3=scn.next().toUpperCase();
			}
			City1="'"+City1+"'";//Convert input values as required for SQL query
			City2="'"+City2+"'";
			City3="'"+City3+"'";
			
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
			if(con!=null)
				st=con.createStatement();
			//prepare for SQL query
			String query = "SELECT ID,NAME,CITY,AVG FROM STUDENT WHERE CITY IN("+City1+","+City2+","+City3+")ORDER BY CITY";
			System.out.println(query);
			
			//send and execute SQL query
			if(st!=null)
				rs=st.executeQuery(query);
			if(rs!=null) {
				System.out.println("The Students details of given cities "+City1+"" +City2+""+ City3+".");
			while(rs.next()!=false) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}
			
			}
		}catch(SQLException se) {
			System.out.println(se.toString());
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
			}catch (SQLException se) {
				se.printStackTrace();{
			
			}
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se1) {
				se1.printStackTrace();
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
}
