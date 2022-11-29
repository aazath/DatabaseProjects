package com.assignment.one;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectApp {

	public static void main(String[] args) throws SQLException {
		// resources needed for the application
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		//connection credentials
		String url = "jdbc:mysql://localhost:3306/enterprisejava";
		String username = "root";
		String password = "";
		
		System.out.println("View all students in the system");
		System.out.println("===============================");
		try {
			connection = DriverManager.getConnection(url,username,password);
			
			if(connection != null)
			{
				//create statement object
				statement = connection.createStatement();
				
				if(statement != null)
				{
					String selectSqlQuery = "select sid,sname,saddr from student";
					
					//using statement object to execute query
					resultSet = statement.executeQuery(selectSqlQuery);
					
					//process the result set to get data
					System.out.println("SID\tSNAME\tSADDR");
					if(resultSet != null)
					{
						while(resultSet.next())
						{
							int sid = resultSet.getInt("sid");
							String sname = resultSet.getString("sname");
							String saddr = resultSet.getString("saddr");
							System.out.println(sid + "\t"+sname+"\t"+saddr);
						}
					}
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
			if(resultSet != null)
			{
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}

}
