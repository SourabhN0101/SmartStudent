package com.finalproject;

import java.util.Scanner;

public class AdminLogin { //(Login page )
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public boolean login() {
        Scanner sc = new Scanner(System.in);
			System.out.println("------ Admin Login ------");
			System.out.print("Enter Username: ");
			String inputUser = sc.nextLine();
			System.out.print("Enter Password: ");
			String inputPass = sc.nextLine();

			if (USERNAME.equals(inputUser) && PASSWORD.equals(inputPass)) {
			    System.out.println("Login successful. Welcome, Sourabh!!!");
			    return true;
			} else {
			    System.out.println("Invalid Username & Password, plzz Try Again.!!!!");
			    return false;
			} 
		}
   }