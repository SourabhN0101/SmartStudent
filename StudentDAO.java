package com.finalproject;

import java.sql.*;
import java.util.*;

public class StudentDAO { //(Data Access Logic)

    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, roll_no, department, email, phone, marks) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getRollNo());
            ps.setString(3, student.getDepartment());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());
            ps.setInt(6, student.getMarks());

            ps.executeUpdate();
            System.out.println("Student added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                list.add(extractStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateStudent(Student s) {
        String sql = "UPDATE students SET name=?, roll_no=?, department=?, email=?, phone=?, marks=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getRollNo());
            ps.setString(3, s.getDepartment());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getPhone());
            ps.setInt(6, s.getMarks());
            ps.setInt(7, s.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student details updated.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudentMarks(int id, int newMarks) {
        String sql = "UPDATE students SET marks=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newMarks);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Marks updated." : " Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted." : "Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractStudent(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getStudentByRollNo(String rollNo) {
        String sql = "SELECT * FROM students WHERE roll_no=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, rollNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractStudent(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> getStudentsByDepartment(String dept) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE department=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dept);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractStudent(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Student> getStudentsByMarksRange(int min, int max) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE marks BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, min);
            ps.setInt(2, max);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractStudent(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalStudentCount() {
        String sql = "SELECT COUNT(*) FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getHighestMarks() {
        String sql = "SELECT MAX(marks) FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLowestMarks() {
        String sql = "SELECT MIN(marks) FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String, Integer> getDepartmentWiseCount() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT department, COUNT(*) FROM students GROUP BY department";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    // Utility method to avoid code duplication
    private Student extractStudent(ResultSet rs) throws SQLException {
        return new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("roll_no"),
            rs.getString("department"),
            rs.getString("email"),
            rs.getString("phone"),
            rs.getInt("marks")
        );
    }
}
