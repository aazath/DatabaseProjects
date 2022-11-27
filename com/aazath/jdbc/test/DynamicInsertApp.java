package com.aazath.jdbc.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DynamicInsertApp {

	public static void main(String[] args) throws SQLException {
		// resources needed for the application
		Connection connection = null;
		Statement statement = null;
		
		//DB connection credentials
		String url = "jdbc:mysql://localhost:3306/enterprisejava";
		String username = "root";
		String password = "";
		
		//get the inputs
		System.out.println("Add a new student to the system");
		System.out.println("===============================");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Student ID : ");
		int sid = scan.nextInt();
		System.out.println("Enter the Student Name : ");
		String sname = scan.next();
		System.out.println("Enter the Student Address : ");
		String saddr = scan.next();
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			
			if(connection != null)
			{
				//create statement object
				statement = connection.createStatement();
				
				if(statement != null)
				{
					String insertSqlQuery = "insert into student values('"+sid+"','"+sname+"','"+saddr+"')";
					
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
