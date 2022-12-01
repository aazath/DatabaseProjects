//This java application will update the detail of a student in the database
package com.assignment.three;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.projects.jdbcUtil.JdbcUtil;


public class UpdateApp {

	public static void main(String[] args) throws SQLException {
		// resources needed for the application
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		//local variables for the student's details
		int sid = 0;
		String sname = null;
		String saddress = null;
		String sgender = null;
		java.sql.Date dob = null;
		java.sql.Date doj = null;
		java.sql.Date dom = null;
		
		
		System.out.println("Update Student's Details");
		System.out.println("========================");
		
		
		//retrieving the student details to update
		System.out.print("Enter the student's name :");
		sname = scan.next();
		
		String selectSqlQuery = "Select sid from student where sname =?";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null)
			{
				pst = connection.prepareStatement(selectSqlQuery);
			}
			if(pst!=null)
			{
				pst.setString(1, sname);
				rs = pst.executeQuery();
			}
			
			if(rs!=null)
			{
				if(rs.next())
				{
					sid = rs.getInt(1);
				}
				else
				{
					System.out.println("Record not available for "+sname);
					
				}
			}}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//getting updated inputs
		System.out.println("Enter the new details to update :");
		System.out.println("Enter the name :");
		sname = scan.next();
		
		System.out.print("Enter the address :");
		saddress = scan.next();
		
		System.out.print("Enter the gender (M/F/O):");
		sgender = scan.next();
		
		System.out.print("Enter DOB :(yyyy-MM-dd)");
		String sdob = scan.next(); //taking the input as string
		dob = java.sql.Date.valueOf(sdob);//converting the string date to sql date
		
		System.out.print("Enter DOJ :(yyyy-MM-dd)");
		String sdoj = scan.next();
		doj = java.sql.Date.valueOf(sdoj);
		
		System.out.print("Enter DOM :(yyyy-MM-dd)");
		String sdom = scan.next();
		dom = java.sql.Date.valueOf(sdom);
		
		
		String updateSqlQuery = "update student set dob = ?,"
				+ "sname = ?,"
				+ "saddr = ?,"
				+ "sgender = ?,"
				+ "doj = ?,"
				+ "dom = ?"
				+ "where sid = ?";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null)
			{
				pst = connection.prepareStatement(updateSqlQuery);
			}
			
			if(pst!=null)
			{
				pst.setDate(1, dob);
				pst.setString(2, sname);
				pst.setString(3, saddress);
				pst.setString(4, sgender);
				pst.setDate(5, doj);
				pst.setDate(6, dom);
				pst.setInt(7, sid);
				
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
			if (scan != null) {
				scan.close();
			}
		}

	}

}
