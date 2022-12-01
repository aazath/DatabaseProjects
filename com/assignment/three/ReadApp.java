//This java application will retrieve a single student's details
//from database based on the given student name
package com.assignment.three;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.projects.jdbcUtil.JdbcUtil;

public class ReadApp {

	public static void main(String[] args) {
		// needed resources
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Scanner scanner = new Scanner(System.in);
		
		//intermediate date format
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String selectSqlQuery = "Select sid,sname,saddr,sgender,dob,doj,dom from student where sname =?";
		
		
		System.out.println("View Student Details");
		System.out.println("====================");
		
		System.out.print("Enter the name of student :");
		String name = scanner.next();
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null)
			{
				pst = connection.prepareStatement(selectSqlQuery);
			}
			if(pst!=null)
			{
				pst.setString(1, name);
				rs = pst.executeQuery();
			}
			
			if(rs!=null)
			{
				if(rs.next())
				{
					int sid = rs.getInt(1);
					String sname = rs.getString(2);
					String saddress = rs.getString(3);
					String sgender = rs.getString(4);
					java.sql.Date dob = rs.getDate(5);
					String sdob = sdf.format(dob);
					java.sql.Date doj = rs.getDate(6);
					String sdoj = sdf.format(doj);
					java.sql.Date dom = rs.getDate(7);
					String sdom = sdf.format(dom);
					
					System.out.println("ID IS 		: "+sid);
					System.out.println("NAME IS 	: "+sname);
					System.out.println("ADDRESS IS 	: "+saddress);
					System.out.println("GENDER IS 	: "+sgender);
					System.out.println("DOB IS 		: "+dob);
					System.out.println("DOJ IS 		: "+doj);
					System.out.println("DOM IS 		: "+dom);
				}
				else
				{
					System.out.println("Record not available for "+name);
					
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
		finally
		{
			try {
				JdbcUtil.closeConnection(rs, pst, connection);
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
			//scanner object could be closed at the main page
//			if(scanner != null)
//			{
//				scanner.close();
//			}
		}
		

	

	}
}
