package sp.storedProcedureApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.CallableStatement;
import com.projects.jdbcUtil.JdbcUtil;

public class getAllStudentsApp {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		System.out.println("List all students :");
		System.out.println("=====================");
		
		
		connection = JdbcUtil.getJdbcConnection();
		String sp = "{call getAllStudents()}";
		try {
			if(connection!=null)
			{
				cstmt = connection.prepareCall(sp);
			}
			if(cstmt!=null)
			{
				cstmt.execute();
				rs = cstmt.getResultSet();
			}
			
			System.out.println("SID\tNAME\tADDRESS");
			System.out.println("========================");
			if(rs!=null)
			{
				while(rs.next())
				{
					int sid = rs.getInt(1);
					String name = rs.getString(2);
					String address = rs.getString(3);
					System.out.println(sid+"\t"+name+"\t"+address);
					
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.closeConnection(rs, cstmt, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
		

}
