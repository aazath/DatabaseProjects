package com.assignment.one;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertApp {

	public static void main(String[] args) throws SQLException {
		// resources needed for the application
		Connection connection = null;
		Statement statement = null;
		
		//establish the connection
		String url = "jdbc:mysql://localhost:3306/enterprisejava";
		String username = "root";
		String password = "";
		
		System.out.println("Add a new student to the system");
		System.out.println("===============================");
		try {
			connection = DriverManager.getConnection(url,username,password);
			
			if(connection != null)
			{
				//create statement object
				statement = connection.createStatement();
				
				if(statement != null)
				{
					String insertSqlQuery = "insert into student values(1,'Aazath','Sri Lanka')";
					
					//using statement object to execute query
					int noOfRowsAffected = statement.executeUpdate(insertSqlQuery);
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
