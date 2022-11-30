package com.assignment.two;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.projects.jdbcUtil.JdbcUtil;

//columns in the database
//name
//address
//gender
//DOB -> dd-MM-yyyy
//DOJ -> MM-dd-yyyy
//DOM -> yyyy-MM-dd

public class DBInsertApp {

	public static void main(String[] args) throws ParseException {
		// resource needed
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insert Student Details");
		System.out.println("======================");
		
		System.out.print("Enter the student's name :");
		String name = scan.next();
		
		System.out.print("Enter the student's address :");
		String address = scan.next();
		
		System.out.print("Enter the student's gender :");
		String gender = scan.next();
		
		System.out.print("Enter the student's DOB :(dd-MM-yyyy)");
		String sdob = scan.next();
		
		SimpleDateFormat sdfdob = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date uDate = sdfdob.parse(sdob);
		long lTime = uDate.getTime();
		java.sql.Date dob = new java.sql.Date(lTime);
		
		
		System.out.print("Enter the student's DOJ :(MM-dd-yyyy)");
		String sdoj = scan.next();
		
		SimpleDateFormat sdfdoj = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date dDOJ = sdfdoj.parse(sdoj);
		long lJ	= dDOJ.getTime();
		java.sql.Date doj = new java.sql.Date(lJ);
		
		System.out.print("Enter the student's DOM :(yyyy-MM-dd)");
		String sdom = scan.next();
		java.sql.Date dom = java.sql.Date.valueOf(sdom);
		
		
		String insertSqlQuery = "insert into student(dob,sname,saddr,sgender,doj,dom) values(?,?,?,?,?,?)";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null)
			{
				pstmt = connection.prepareStatement(insertSqlQuery);
			}
			
			if(pstmt!=null)
			{
				pstmt.setDate(1, dob);
				pstmt.setString(2, name);
				pstmt.setString(3, address);
				pstmt.setString(4, gender);
				pstmt.setDate(5, doj);
				pstmt.setDate(6, dom);
				
				int noOfRows = pstmt.executeUpdate();
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
				JdbcUtil.closeConnection(null, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (scan != null) {
				scan.close();
			}
		}
		
		
		
		
		
		
		
		

	}

}
