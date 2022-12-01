package com.assignment.three;

import java.sql.SQLException;
import java.util.Scanner;
import com.aazath.jdbc.test.*;

public class IneuronSMSApp {

	public static void main(String[] args) throws SQLException {
		
		//to get the choice
		int input;
		Scanner scanner = new Scanner(System.in);
		try {
			do {
				//show the menu
				System.out.println("INeuron Student Management System");
				System.out.println("====================================");
				System.out.println("1. Add a new student");
				System.out.println("2. Update a student");
				System.out.println("3. View a student");
				System.out.println("4. Delete a student");
				System.out.println("5. View all students");
				System.out.println("Enter your choice of operation (1-5) : ");
				input = scanner.nextInt();
				switch (input) {
				case 1: 
					//call IsertApp
					InsertApp.main(null);				
					break;
				case 2: 
					//call UpdateApp
					UpdateApp.main(null);				
					break;
				case 3: 
					//call ReadApp
					ReadApp.main(null);
					break;
				case 4: 
					//call DeleteApp
					DeleteApp.main(null);
					break;
				case 5: 
					//call ListAllApp
					ListAllApp.main(null);
					break;
				default:
					System.out.println("Illegal input of choice, enter 1 -5 :");
				}
			
		}
		while(input != 0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if(scanner!=null) {
				scanner.close();
			}
		}
		
	}

}
