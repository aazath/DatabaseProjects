package com.assignment.three;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.projects.jdbcUtil.JdbcUtil;

public class ListAllApp {

	public static void main(String[] args) {
		// resources needed
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		//creating the simple date format object to be used as intermediary date format
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		
		String selectSqlQuery = "select sid,sname,saddr,sgender,dob,doj,dom from student";
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null)
			{
				pst = connection.prepareStatement(selectSqlQuery);
			}
			if(pst!=null) {
				rs = pst.executeQuery();
				System.out.println("ID\tName\tAddress\t\tGender\tDOB\t\tDOJ\t\tDOM");
				while(rs.next()) {
					int sid = rs.getInt(1);
					String sname = rs.getString(2);
					String saddr = rs.getString(3);
					String sgender = rs.getString(4);
					java.sql.Date sdob = rs.getDate(5);
					String dob = sdf.format(sdob);
					java.sql.Date sdoj = rs.getDate(6);
					String doj = sdf.format(sdoj);
					java.sql.Date sdom = rs.getDate(7);
					String dom = sdf.format(sdom);
					
					System.out.println(sid +"\t"+sname+"\t"+saddr+"\t"+sgender+"\t"+dob+"\t"+doj+"\t"+dom);
					
				}
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.closeConnection(rs, pst, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
