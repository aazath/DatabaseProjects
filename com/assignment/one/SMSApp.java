package com.assignment.one;

import java.sql.SQLException;
import java.util.Scanner;
import com.aazath.jdbc.test.*;

public class SMSApp {

	public static void main(String[] args) throws SQLException {
		//to get the choice
		int input;
		do {
			//show the menu
			System.out.println("Welcome to Student Management System");
			System.out.println("====================================");
			System.out.println("Enter 1 to add a new student");
			System.out.println("Enter 2 to update a student");
			System.out.println("Enter 3 to view students");
			System.out.println("Enter 4 to delete a student");
			System.out.println("Enter your choice of operation (1-4) : ");
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
			switch (input) {
			case 1: 
				//call dynamicIsertApp
				DynamicInsertApp.main(null);				
				break;
			case 2: 
				//call UpdateApp
				UpdateApp.main(null);				
				break;
			case 3: 
				//call SelectApp
				SelectApp.main(null);
				break;
			case 4: 
				//call DeleteApp
				DeleteApp.main(null);
				break;
			default:
				System.out.println("Illegal input of choice, enter 1 -4 :");
			}
			
		}
		while(input != 0);
		
	}

}
