package com.partice.jdbc;
import java.sql.*;//for jdbc api
import java.util.Scanner;//for scanner
public class Test01_SelectQuery {

	public static void main(String[] args)throws Exception {
		// read inputs
		Scanner sc=new Scanner(System.in);

		System.out.println("Enter start range of employee salary::");
		float startSalary=sc.nextFloat();
		System.out.println("Enter end range of employee salary::");
		float endSalary=sc.nextFloat();
	
		//register JDBC driver s/w by load jdbc Driver class(optional)
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//Establish the connection with Oracle Db s/w
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
	    
		//create statement
		Statement st=con.createStatement();
		//prepare SQL query
		String query=" SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>="+startSalary+"AND SAL<="+endSalary;
		System.out.println(query);
		//send and execute SQL Query in db s/w
		ResultSet rs=st.executeQuery(query);
		//process the ResultSet obj
	    System.out.println("Emp details having salary range"+startSalary+"...."+endSalary);
		while(rs.next()!=false) {
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));

		
		}
		//close jdbc objs
		rs.close();
		st.close();
		con.close();
	}//main
}//class
//>javac Test01.java
//>java Test01