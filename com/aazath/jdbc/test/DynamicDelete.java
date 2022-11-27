package com.aazath.jdbc.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DynamicDelete {

	public static void main(String[] args) throws SQLException {
		// resources needed for the application
		Connection connection = null;
		Statement statement = null;
		
		//connection credentials
		String url = "jdbc:mysql://localhost:3306/enterprisejava";
		String username = "root";
		String password = "";
		
		System.out.println("Deleting a student's Details ");
		System.out.println("=============================");
		//get the id for deletion
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Student ID : ");
		int sid = scan.nextInt();
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			
			if(connection != null)
			{
				//create statement object
				statement = connection.createStatement();
				
				if(statement != null)
				{
					String deleteSqlQuery = "delete from student where sid ='"+sid+"'";
					
					//using statement object to execute query
					int noOfRowsAffected = statement.executeUpdate(deleteSqlQuery);
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
