package com.mysql.partice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Test_Mysql {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	
		//oracle.jdbc.driver.OracleDriver obj=new Oracle.jdbc.driver.OracleDriver();
		//DriverManager.registerDriver(obj);
	//	Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/userlogin";
		Connection con = DriverManager.getConnection(url, "root","root");
	//	System.out.println("con object classname:"+con.getClass());
		
		
		if (con==null)
			System.out.println("CONNECTION ERROR");
		else
			System.out.println("CONNECTION DONE");
		
		//create statement
		Statement st=con.createStatement();
		//System.out.println("Statament object(st) class name:"+st.getClass());
	
		ResultSet rs=st.executeQuery("select * from student ");
		while(rs.next()!=false) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2));
		}
		System.out.println("Sucessifully retrive");
	
		rs.close();
		st.close();
		con.close();
	}
}