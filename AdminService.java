package com.finalproject;

import java.util.List;
import java.util.Map;

public class AdminService { //(CRUD Logic)

    private StudentDAO dao = new StudentDAO();

    // Add a new student
    public void addStudent(Student student) {
        dao.addStudent(student);
    }

    //  View all students with complete details
    public void viewAllStudents() {
        List<Student> list = dao.getAllStudents();

        if (list.isEmpty()) {
            System.out.println(" No students found.");
            return;
        }

        System.out.println("ID | Name | Roll No | Department | Email | Phone | Marks");
        System.out.println("---------------------------------------------------------------------");

        for (Student s : list) {
            System.out.println(
                s.getId() + " | " +
                s.getName() + " | " +
                s.getRollNo() + " | " +
                s.getDepartment() + " | " +
                s.getEmail() + " | " +
                s.getPhone() + " | " +
                s.getMarks()
            );
        }
    }

    //  Get student by ID (used for update or view single)
    public Student getStudentById(int id) {
        return dao.getStudentById(id);
    }

    // Update the entire student record (name, roll, email, etc.)
    public void updateStudent(Student updated) {
        dao.updateStudent(updated);
    }

    //Update only marks of student (optional use-case)
    public void updateStudentMarks(int id, int newMarks) {
        dao.updateStudentMarks(id, newMarks);
    }

    // Delete a student by ID
    public void deleteStudent(int id) {
        dao.deleteStudent(id);
    }
    public Student getStudentByRollNo(String rollNo) {
        return dao.getStudentByRollNo(rollNo);
    }
    public void searchByDepartment(String dept) {
        List<Student> list = dao.getStudentsByDepartment(dept);
        if (list.isEmpty()) {
            System.out.println("No students found in department: " + dept);
            return;
        }

        for (Student s : list) {
            System.out.println(s.getId() + " | " + s.getName() + " | " + s.getDepartment() + " | " + s.getMarks());
        }
    }
    public void searchByMarksRange(int min, int max) {
        List<Student> list = dao.getStudentsByMarksRange(min, max);
        for (Student s : list) {
            System.out.println(s.getId() + " | " + s.getName() + " | " + s.getMarks());
        }
    }
    public void showStatistics() {
        System.out.println("Total Students: " + dao.getTotalStudentCount());
        System.out.println("Highest Marks: " + dao.getHighestMarks());
        System.out.println("Lowest Marks: " + dao.getLowestMarks());
        
        System.out.println("Department-wise Count:");
        Map<String, Integer> map = dao.getDepartmentWiseCount();
        for (String dept : map.keySet()) {
            System.out.println(dept + ": " + map.get(dept));
        }
    }

}
