//This app will delete the details of a student
package com.assignment.three;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.projects.jdbcUtil.JdbcUtil;

public class DeleteApp {

	public static void main(String[] args) {
		// resources
		Connection connection = null;
		PreparedStatement pst = null;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Delete a student's record");
		System.out.println("=========================");
		
		System.out.print("Enter the name of the student :");
		String name = scanner.next();
		
		String deleteSqlQuery = "delete from student where sname = ?";
		int rowsAffected = 0;
		
		try {
			//establish the connection
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null)
			{
				//create the prepared statement
				pst = connection.prepareStatement(deleteSqlQuery);
			}
			if(pst != null)
			{
				//set the parameters for prepared statement
				pst.setString(1, name);
				
				//execute the query
				rowsAffected = pst.executeUpdate();
			}
			if(rowsAffected !=0) {
				System.out.println("Records of "+name +" has deleted successfully ");
			}
			else {
				System.out.println("Record not available for "+name);
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing the resources
		finally {
			try {
				JdbcUtil.closeConnection(null, pst, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (scanner != null) {
				scanner.close();
			}
		}

	}

}
