package com.partice.jdbc;
import java.sql.*;

public class Test
{
	public static void main(String[] args)throws ClassNotFoundException, SQLException{
		//oracle.jdbc.driver.oracleDriver obj=new oracle.jdbc.driver.oracleDriver();
		//DriverManager.registerDriver(obj);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
    if(con==null)
		System.out.println("Error");
	else
  System.out.println("Done");
	}
}
