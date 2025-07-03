package com.finalproject;

import java.util.*;

public class UI { // (Console UI or GUI)
    private AdminService service = new AdminService();
    private Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("====== SmartStudent Management System ======");
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search by Roll No");
            System.out.println("6. Search by Department");
            System.out.println("7. Search by Marks Range");
            System.out.println("8. Show Statistics");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1 -> addStudent();
                case 2 -> service.viewAllStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> searchByRollNo();
                case 6 -> searchByDepartment();
                case 7 -> searchByMarksRange();
                case 8 -> service.showStatistics();
                case 9 -> {
                    System.out.println("Exit From Option, See You Soon!!!");
                    return;
                }
                default -> System.out.println("Plzz Choose Correct Option, Try Again!!!");
            }
        }
    }

    private void addStudent() {
        Student s = inputStudentData(0); // 0 for new student
        service.addStudent(s);
    }

    private void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Student existing = service.getStudentById(id);
        if (existing != null) {
            System.out.println("Updating details for: " + existing.getName());
            Student updated = inputStudentData(id);
            service.updateStudent(updated);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        service.deleteStudent(id);
    }

    private void searchByRollNo() {
        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();
        Student s = service.getStudentByRollNo(roll);
        if (s != null) {
            System.out.println("Found: " + s.getId() + " | " + s.getName() + " | " + s.getMarks());
        } else {
            System.out.println("Student not found.");
        }
    }

    private void searchByDepartment() {
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        service.searchByDepartment(dept);
    }

    private void searchByMarksRange() {
        System.out.print("Enter Min Marks: ");
        int min = sc.nextInt();
        System.out.print("Enter Max Marks: ");
        int max = sc.nextInt();
        sc.nextLine();
        service.searchByMarksRange(min, max);
    }

    private Student inputStudentData(int id) {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Roll No: ");
        String roll = sc.nextLine();

        System.out.print("Department: ");
        String dept = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        System.out.print("Marks: ");
        int marks = sc.nextInt();
        sc.nextLine(); 

        return new Student(id, name, roll, dept, email, phone, marks);
    }
}
