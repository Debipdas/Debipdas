package com.nit.jdbc;
// JDBC LOGIN APP  with console

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginApp1 {

	public static void main(String[] args) {
		Console cons = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// read inputs

			cons=System.console();
			String usn = null, pwd = null;

			if (cons != null) {
				System.out.println("ENTER USERNAME::");
				usn = cons.readLine();
				System.out.println("ENTER PASSWORD::");
				pwd = new String(cons.readPassword());
			}
			// convert input values as required for the SQL query
			usn = "'" + usn + "'";
			pwd = "'" + pwd + "'";

			// register JDBC driver by loading JDBC driver class
			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");

			// create Statement object
			if (con != null)
				st = con.createStatement();

			// prepare SQL query
			// SQL> SELECT COUNT(*) FROM IRCTC_TAB WHERE USRNAME= 'devil' AND
			// PASSWD='lapun';
			String query = "SELECT COUNT(*) FROM IRCTC_TAB WHERE USRNAME= " + usn + " AND PASSWD=" + pwd;
			System.out.println(query);

			// send and execute SQL query in db s/w

			if (st != null)
				rs = st.executeQuery(query);

			// process the result
			if (rs != null) {
				rs.next();//moves the cursor to first from BFR
				int count = rs.getInt(1);
				//process the result
				if (count == 0)
					System.out.println("INVALID CREDENTIALS");
				else
					System.out.println("VALID CREDENTAILS");

			} // if

		} // try
		catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			

		} // finally

	}// main

}// class