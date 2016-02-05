package com.lftechnology.batch7crud.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.util.DBConnection;

/**
 * Created by sagarmatha on 1/26/16.
 */
public class StudentDaoImpl implements StudentDao {
  private static final Logger logger = Logger.getLogger(StudentDaoImpl.class.getName());

  private static final String INSERT = "INSERT INTO studentinfo (fname,lname,age,address) VALUES (?,?,?,?)";
  private static final String VIEW = "SELECT * FROM studentinfo LIMIT ? OFFSET ?";
  private static final String UPDATE = "UPDATE studentinfo SET fname=?, lname=?, age=?, address=? WHERE id=?";
  private static final String VIEW_BY_ID = "SELECT * FROM studentinfo WHERE id=?";
  private static final String DELETE = "DELETE FROM studentinfo WHERE id=?";
  private static final String COUNT_STUDENTS = "SELECT count(*) FROM studentinfo";
  Connection connection;
  PreparedStatement preparedStatement;


  public Student addStudent(Student student) {
    try {
      connection = DBConnection.getDBConnection();
      preparedStatement = connection.prepareStatement(INSERT);
      preparedStatement.setString(1, student.getFirstName());
      preparedStatement.setString(2, student.getLastName());
      preparedStatement.setInt(3, student.getAge());
      preparedStatement.setString(4, student.getAddress());
      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        student.setStudentID(resultSet.getInt(1));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
      }
    }
    return student;
  }

  public void deleteStudent(int studentID) {
    try {
      connection = DBConnection.getDBConnection();
      preparedStatement = connection.prepareStatement(DELETE);
      preparedStatement.setInt(1, studentID);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
      }
    }
  }

  public Student updateStudent(Student student) {
    try {
      connection = DBConnection.getDBConnection();
      preparedStatement = connection.prepareStatement(UPDATE);
      preparedStatement.setString(1, student.getFirstName());
      preparedStatement.setString(2, student.getLastName());
      preparedStatement.setInt(3, student.getAge());
      preparedStatement.setString(4, student.getAddress());
      preparedStatement.setInt(5, student.getStudentID());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
      }
    }
    return student;
  }

  public List<Student> getAllStudents(int page, int limit) {
    List<Student> studentsList = new ArrayList<Student>();
    ResultSet resultSet = null;
    try {
      connection = DBConnection.getDBConnection(); // NOSONAR
      preparedStatement = connection.prepareStatement(VIEW); //NOSONAR
      int startOffset = (page - 1) * limit;
      preparedStatement.setInt(1, limit);
      preparedStatement.setInt(2, startOffset);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Student student = new Student();
        student.setStudentID(resultSet.getInt("id"));
        student.setFirstName(resultSet.getString("fName"));
        student.setLastName(resultSet.getString("lName"));
        student.setAge(resultSet.getInt("age"));
        student.setAddress(resultSet.getString("address"));
        studentsList.add(student);
      }
      resultSet.close();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
      }
    }
    return studentsList;
  }

  public Student getStudentByID(int studentID) {
    Student student = null;
    try {
      connection = DBConnection.getDBConnection();
      preparedStatement = connection.prepareStatement(VIEW_BY_ID);
      preparedStatement.setInt(1, studentID);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        student = new Student();
        student.setStudentID(rs.getInt(1));
        student.setFirstName(rs.getString(2));
        student.setLastName(rs.getString(3));
        student.setAge(rs.getInt(4));
        student.setAddress(rs.getString(5));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
      }
    }
    return student;
  }

  public int countStudents() {
    int totalStudents = 0;
    try {
      connection = DBConnection.getDBConnection(); // NOSONAR
      preparedStatement = connection.prepareStatement(COUNT_STUDENTS); // NOSONAR
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        totalStudents = resultSet.getInt(1);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
      }
    }
    return totalStudents;
  }

  public boolean findStudent(int studentID) {
    boolean statusCheck = false;
    try {
      connection = DBConnection.getDBConnection();
      preparedStatement = connection.prepareStatement(VIEW_BY_ID);
      preparedStatement.setInt(1, studentID);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        statusCheck = true;
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
      }
    }
    return statusCheck;
  }
}

