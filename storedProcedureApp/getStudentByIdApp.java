package storedProcedureApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import java.sql.CallableStatement;
import com.projects.jdbcUtil.JdbcUtil;
/*
 	CREATE DEFINER=`root`@`localhost` PROCEDURE `enterprisejava`.`getStudentsById`(IN id INT,OUT stdName VARCHAR(20),OUT stdAddr VARCHAR(20))
	BEGIN
		SELECT sname,saddr INTO stdName,stdAddr 
		FROM student
		WHERE sid = id;
	END
	calling the stored procedure:
	CALL getStudentsById(2,@name,@addr);
	SELECT @name as 'Name', @addr as 'Add';
 */

public class getStudentByIdApp {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		Scanner scanner = new Scanner(System.in);
		int id;
		
		System.out.println("Searching a student :");
		System.out.println("=====================");
		System.out.print("Enter the student ID :");
		id = scanner.nextInt();
		
		
		connection = JdbcUtil.getJdbcConnection();
		String sp = "{call getStudentsById(?,?,?)}";
		try {
			if(connection!=null)
			{
				cstmt = connection.prepareCall(sp);
			}
			if(cstmt!=null)
			{
				//setting up the input parameters
				cstmt.setInt(1, id);
				//setting up the output parameters with java specific data types
				cstmt.registerOutParameter(2, Types.VARCHAR);//this will internally convert varchar to String
				cstmt.registerOutParameter(3, Types.VARCHAR);
				
				//execute the query
				cstmt.execute();
				
				//retrieving the values
				System.out.println("Name of the Student is :: "+cstmt.getString(2));
				System.out.println("Address of the Student is :: "+cstmt.getString(3));
				
			}
			else {
				System.out.println("Record not available for the id "+id);
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
			if(scanner!=null)
			{
				scanner.close();
			}
		}
		
	}
		
		

}
