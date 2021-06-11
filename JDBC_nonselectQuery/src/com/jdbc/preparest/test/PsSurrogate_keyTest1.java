package com.jdbc.preparest.test;

//JDBC APP TO INSERT STUDENT DEATILS IN DB TABLE WITH USE OF SURROGATE KEY(Primary Key).
//TEAM-JAVA

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsSurrogate_keyTest1 {

	private static final String STUDENT_QUERY_STRING = "INSERT INTO STUDENT VALUES(SNO_SQ1.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;

		try {
			sc = new Scanner(System.in);
			if (sc != null) {

				System.out.println("Enter student's count::");
				count = sc.nextInt();

			}
			// class load
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish
			con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");

			// create prepare statement
			if (con != null)
				ps = con.prepareStatement(STUDENT_QUERY_STRING);

			// read input from end user
			// SQL query for multiple times
			if (ps != null && sc != null) {
				for (int i = 1; i <= count; ++i) {
					// read each student input values
					System.out.println("enter " + i + " student details");
					System.out.println("Enter Student's Name:  ::");
					String Name = sc.next();
					System.out.println("Enter Student's address::");
					String City = sc.next();
					System.out.println("Enter Student's avg pre::");
					float Avg = sc.nextFloat();

					// set each students value
					ps.setString(1, Name);
					ps.setString(2, City);
					ps.setFloat(3, Avg);

					// execute SQL query each times
					int result = ps.executeUpdate();

					if (result == 0)
						System.out.println(i + " student details not inserted");
					else
						System.out.println(i + " student details are inserted");

				} // for

			} // if//
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null)
					ps.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			try {
				if(sc!=null)
					sc.close();
			}catch (Exception e) {
                e.printStackTrace();
			}
			}
		}//finally
	}//main
}//class
