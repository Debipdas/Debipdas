package com.jdbc.select.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub

		String url = "jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe";
		Connection con = DriverManager.getConnection(url, "SYSTEM","TIGER");
		
		if(con==null)
			System.out.println("some error");
		else
			System.out.println("Sucessifully connect!!!!");
		//Statement st=con.createStatement();
		//ResultSet rs=st.executeQuery("Select * from Student");
		//System.out.println("Retriveing process started!!!!");
		//while(rs.next()!=false) {
	     		
			//System.out.println(rs.getInt(1)+" "+rs.getString(2));
			//System.out.println(rs.getInt("ID")+" "+rs.getString("NAME"));
		//	System.out.println(rs.getString("ID")+" "+rs.getString("NAME"));   
		//}
		//System.out.println("Done");
	//rs.close();
	//st.close();
	con.close();
	}

}
