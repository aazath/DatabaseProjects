//This application will insert a new student into the database
package com.assignment.three;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.projects.jdbcUtil.JdbcUtil;


public class InsertApp {

	public static void main(String[] args) throws SQLException {
		// create the resources needed for the application
		Connection connection = null;
		PreparedStatement pst = null;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Add a new student to the system");
		System.out.println("===============================");
		
		System.out.print("Enter the student's name :");
		String name = scan.next();
		
		System.out.print("Enter the student's address :");
		String address = scan.next();
		
		System.out.print("Enter the student's gender (M/F/O):");
		String gender = scan.next();
		
		System.out.print("Enter the student's DOB :(yyyy-MM-dd)");
		String sdob = scan.next();
		java.sql.Date dob = java.sql.Date.valueOf(sdob);		
		
		System.out.print("Enter the student's DOJ :(yyyy-MM-dd)");
		String sdoj = scan.next();
		java.sql.Date doj = java.sql.Date.valueOf(sdoj);
		
		System.out.print("Enter the student's DOM :(yyyy-MM-dd)");
		String sdom = scan.next();
		java.sql.Date dom = java.sql.Date.valueOf(sdom);
		
		String insertSqlQuery = "insert into student(dob,sname,saddr,sgender,doj,dom) values(?,?,?,?,?,?)";
		
		try {
			//get the connection
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null)
			{
				//get the prepared statement object
				pst = connection.prepareStatement(insertSqlQuery);
			}
			
			if(pst!=null)
			{
				//setup the parameters for the prepared statement
				pst.setDate(1, dob);
				pst.setString(2, name);
				pst.setString(3, address);
				pst.setString(4, gender);
				pst.setDate(5, doj);
				pst.setDate(6, dom);
				
				int noOfRows = pst.executeUpdate();
				System.out.println("No of rows affected :"+noOfRows);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.closeConnection(null, pst, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//scanner object can be closed in the Home page
//			if (scan != null) {
//				scan.close();
//			}
		}

	}

}
