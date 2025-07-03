package com.finalproject;

public class Main {
    public static void main(String[] args) {
        System.out.println("**------------------------------------------------------------------**");
        System.out.println("  Welcome to SmartStudent System - Created by Sourabh Navale ");
        System.out.println("**------------------------------------------------------------------**");
        
        AdminLogin login = new AdminLogin();
        if (login.login()) {
            UI ui = new UI();
            ui.start();
        } else {
            // System.out.println("Exiting application.");
        }
    }
}
