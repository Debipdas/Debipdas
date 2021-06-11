package com.jdbc.preparest.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestTry_with_Resources {

	private static final String STUDENT_QUERY_STRING = "INSERT INTO STUDENT VALUES(SNO_SQ2.NEXTVAL,?,?,?)";
	

	public static void main(String[] args) throws SQLException {

		Scanner scn=new Scanner(System.in);
		int count = 0;
		
		try (

				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM",
						"TIGER");
				PreparedStatement ps = con.prepareStatement(STUDENT_QUERY_STRING);

		) {

			
			if (scn != null) {
				System.out.println("Enter student's count::");
				count = scn.nextInt();
			}
			
			if (ps != null && scn != null) {
				for (int i = 1; i <= count; ++i) {
					// read each student input values
					System.out.println("enter " + i + " student details");
					System.out.println("Enter Student's Name:  ::");
					String Name = scn.next();
					System.out.println("Enter Student's address::");
					String City = scn.next();
					System.out.println("Enter Student's avg pre::");
					float Avg = scn.nextFloat();

					// set each students value
					ps.setString(1, Name);
					ps.setString(2, City);
					ps.setFloat(3, Avg);
					
					int result = ps.executeUpdate();

					if (result == 0)
						System.out.println(i + " student details not inserted");
					else
						System.out.println(i + " student details are inserted");
	
				}//for
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
