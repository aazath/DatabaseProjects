package com.assignment.one;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateApp {

	public static void main(String[] args) throws SQLException {
		// resources needed for the application
		Connection connection = null;
		Statement statement = null;
		
		//establish the connection
		String url = "jdbc:mysql://localhost:3306/enterprisejava";
		String username = "root";
		String password = "";
		
		System.out.println("Updating a student's Details ");
		System.out.println("=============================");
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			
			if(connection != null)
			{
				//create statement object
				statement = connection.createStatement();
				
				if(statement != null)
				{
					String updateSqlQuery = "update student set saddr = 'Kalmunai' where sid =1";
					
					//using statement object to execute query
					int noOfRowsAffected = statement.executeUpdate(updateSqlQuery);
					System.out.println("No of rows affected : "+noOfRowsAffected);
					
					
				}
			}
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//close the resources
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}

}
