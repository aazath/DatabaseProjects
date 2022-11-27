package com.aazath.sms;
//standard steps followed in JDBC Application
//1. Load and Register the Driver
//2. Establish the connection
//3. Create a statement object
//4. Send and execute query
//5. Process the result from ResultSet
//6. Close the connection

//import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class Launch1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//load and register the driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			System.out.println("Driver registered successfully");
			
			//Establish the connection
			//JDBC URL Syntax:: <mainprotocol>:<subprotocol>:<subname>
			String url = "jdbc:mysql://localhost:3306/school";
			String password = "";
			String username = "root";
			
			Connection connection = DriverManager.getConnection(url,username,password);
			System.out.println("Connection object is created:: "+ connection);
			System.out.println("Implementation class of the connection interface :: "+ connection.getClass().getName());
			
			//3. Create a statement object
			Statement statement = connection.createStatement();
			System.out.println("Statement object is created:: "+ statement);

			//4. Send and execute query
			String sqlSelectQuery = "select No,Name,sex,class from student";
			ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
			System.out.println("ResultSet object is created:: "+ resultSet);

			//5. Process the result from ResultSet
			System.out.println("\tSID\tNAME\t\tSEX\tCLASS");
			while(resultSet.next()) {
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String sex = resultSet.getString(3);
				String st_class = resultSet.getString(4);
				System.out.println("\t"+id+"\t"+name+"\t\t"+sex+"\t"+st_class);
				
			}
			
			//6. Close the connection
			connection.close();
			System.out.println("Closing the connection...");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
