package com.lftechnology.batch7crud.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.util.DBConnection;
//import com.lftechnology.batch7crud.util.DBConnection;

/**
 * Created by sagarmatha on 1/26/16.
 */
public class StudentDaoImpl implements StudentDao{
    private Connection connection=null;
    Student student;

    public StudentDaoImpl() {
        connection = DBConnection.getDBConnection();
    }
    public void addStudent(Student student){
        try {
            String sql = "insert into student(fname,lname,age,address)values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getAddress());
            int rowsInserted = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteStudent(int studentID){
        try {
            String sql = "delete from student where studentId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateStudent(Student student){
        try {
            String sql = "UPDATE student SET fname=?, lname=?, age=?, address=? WHERE fname=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getAddress());
            int rowsUpdated = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<Student>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from student" );
            while( resultSet.next() ) {
                Student student = new Student();
                student.setStudentID( resultSet.getInt( "id" ) );
                student.setFirstName( resultSet.getString( "fname" ) );
                student.setLastName( resultSet.getString( "lname" ) );
                student.setAge( resultSet.getInt( "age" ) );
                student.setAddress( resultSet.getString( "address" ) );
                students.add(student);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
