package advancedJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;


public class ConnectionPoolingApp {

	public static void main(String[] args) throws SQLException {
		// creating a pooled connection object
		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
		
		ds.setUrl("jdbc:mysql://localhost:3306/enterprisejava");
		ds.setUser("root");
		ds.setPassword("");
		
		//logical connection brought from connection pool
		Connection connection = ds.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("select sid, sname,saddr from student");
		System.out.println("SID\tNAME\tADDRESS");
		System.out.println("=======================");
		while(resultSet.next())
		{
			int sid = resultSet.getInt(1);
			String sname = resultSet.getString(2);
			String saddr = resultSet.getString(3);
			
			System.out.println(sid+"\t"+sname+"\t"+saddr);
		}
		//Sending the connection object back to the tool
		connection.close();

	}

}
