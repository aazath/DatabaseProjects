
//This file has utility functions to establish the database connection
//and close the resources used in the application
package com.projects.jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	private JdbcUtil() {

	}

	// static method that establishes the db connection
	public static Connection getJdbcConnection() {
		// resources used in jdbc application
		Connection connection = null;

		// Establish the Connection
		String url = "jdbc:mysql://localhost:3306/enterprisejava";
		String user = "root";
		String password = "";

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (connection != null)
			return connection;

		return connection;
	}

	// method that closes the resources used in the file
	public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection)
			throws SQLException {

		if (resultSet != null) {
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
