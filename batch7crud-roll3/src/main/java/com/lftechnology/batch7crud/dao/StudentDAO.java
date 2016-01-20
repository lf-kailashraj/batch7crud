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
    String sql = "insert into Students(roll, name) values (?,?)";

    try (Connection conn = DBConnection.getConnection(); PreparedStatement stmnt = conn.prepareStatement(sql);) {
      stmnt.setInt(1, student.getRoll());
      stmnt.setString(2, student.getName());
      stmnt.execute();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }

  public List<Student> fetch(int page, int pageSize) throws DataException {
    String sql = "select * from Students limit ? offset ?";
    List<Student> stdList = new ArrayList<Student>();

    try (Connection conn = DBConnection.getConnection(); PreparedStatement stmnt = conn.prepareStatement(sql);) {
      stmnt.setInt(1, pageSize);
      stmnt.setInt(2, (page - 1) * pageSize);

      try (ResultSet result = stmnt.executeQuery();) {
        while (result.next()) {
          Student student = new Student();
          student.setId(result.getInt("id"));
          student.setRoll(result.getInt("roll"));
          student.setName(result.getString("name"));
          stdList.add(student);
        }
      }
      
      return stdList;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }

  public int fetchTotal() throws DataException {
    String sql = "SELECT COUNT(*) AS total FROM Students";
    int totalSize = 0;

    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmnt = conn.prepareStatement(sql);
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
    String sql = "Select * from Students where id=?";
    Student student = null;

    try (Connection conn = DBConnection.getConnection(); PreparedStatement stmnt = conn.prepareStatement(sql);) {
      stmnt.setInt(1, id);

      try (ResultSet result = stmnt.executeQuery();) {

        while (result.next()) {
          student = new Student();
          student.setId(result.getInt("id"));
          student.setRoll(result.getInt("roll"));
          student.setName(result.getString("name"));
        }
        return student;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }

  public void edit(Student student, int id) throws DataException {
    String sql = "Update Students set roll=?, name=? where id=?";

    try (Connection conn = DBConnection.getConnection(); PreparedStatement stmnt = conn.prepareStatement(sql);) {
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
    String sql = "Delete from Students where id=?";

    try (Connection conn = DBConnection.getConnection(); PreparedStatement stmnt = conn.prepareStatement(sql);) {
      stmnt.setInt(1, id);
      stmnt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
  }
}
