package sp.storedProcedureApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.CallableStatement;
import com.projects.jdbcUtil.JdbcUtil;

public class getMaleStudentsApp {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		System.out.println("Searching a student :");
		System.out.println("=====================");
		
		connection = JdbcUtil.getJdbcConnection();
		String sp = "{call getFemales()}";
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
			
			else {
				System.out.println("Record not found ");
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
