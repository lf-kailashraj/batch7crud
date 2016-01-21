package com.lftechnology.batch7crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;

public class StudentDAO {
  private static final Logger LOGGER = Logger.getLogger("StudentDAO");

  public void insert(Student student) throws DataException {
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("insert into Students(roll, name) values (?,?)")) {
      stmnt.setInt(1, student.getRoll());
      stmnt.setString(2, student.getName());
      stmnt.execute();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }

  public List<Student> fetch(int page, int pageSize) throws DataException {
    List<Student> studentList = new ArrayList<Student>();

    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("select * from Students LIMIT ? OFFSET ?")) {
      stmnt.setInt(1, pageSize);
      stmnt.setInt(2, (page - 1) * pageSize);

      try (ResultSet result = stmnt.executeQuery();) {
        while (result.next()) {
          Student student = new Student();
          student.setId(result.getInt(1));
          student.setRoll(result.getInt(2));
          student.setName(result.getString(3));
          studentList.add(student);
        }
      }
      return studentList;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }

  public int fetchTotal() throws DataException {
    int totalSize = 0;

    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("SELECT COUNT(*) AS total FROM Students");
        ResultSet result = stmnt.executeQuery();) {
      while (result.next()) {
        totalSize = result.getInt("total");
      }
      return totalSize;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }

  public Student fetchStudentById(int id) throws DataException {
    Student student = null;

    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("Select * from Students where id=?")) {
      stmnt.setInt(1, id);

      try (ResultSet result = stmnt.executeQuery();) {

        while (result.next()) {
          student = new Student();
          student.setId(result.getInt(1));
          student.setRoll(result.getInt(2));
          student.setName(result.getString(3));
        }
        return student;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }

  public void edit(Student student, int id) throws DataException {
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("Update Students set roll=?, name=? where id=?")) {
      stmnt.setInt(1, student.getRoll());
      stmnt.setString(2, student.getName());
      stmnt.setInt(3, id);
      stmnt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }

  public void delete(int id) throws DataException {
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("Delete from Students where id=?")) {
      stmnt.setInt(1, id);
      stmnt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }
}
