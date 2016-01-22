package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DbUtilities;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leapfrog on 1/18/16.
 */
public class StudentDataAccess {
  private static final Logger LOGGER = Logger.getLogger(StudentDataAccess.class.getName());
  private static final String INSERT = "insert into students (name,address,roll) values (?,?,?);";
  private static final String FETCH_ALL = "select * from students limit ? offset ?";
  private static final String COUNT = "select count(*) as total from students";
  private static final String FETCH_BY_ID = "select * from students where id = ?";
  private static final String UPDATE = "update students set name = ?, address = ?, roll = ? where id = ?";
  private static final String DELETE = "delete from students where id = ?";

  public void addNew(Student student) throws DataException {
    try (Connection conn = DbUtilities.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, student.getName());
      ps.setString(2, student.getAddress());
      ps.setInt(3, student.getRoll());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if(rs.next()) {
        student.setId(rs.getInt(1));
      }
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  public List<Student> fetch(int limit, int offset) throws DataException {
    try (Connection conn = DbUtilities.getConnection(); PreparedStatement ps = conn.prepareStatement(FETCH_ALL)) {
      List<Student> studentList = new ArrayList<Student>();
      ps.setInt(1, limit);
      ps.setInt(2, offset);

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        studentList.add(student);
      }
      return studentList;

    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public int fetchTotal() throws DataException {
    try (Connection conn = DbUtilities.getConnection(); PreparedStatement ps = conn.prepareStatement(COUNT)) {
      int totalSize = 0;
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        totalSize = rs.getInt("total");
      }
      return totalSize;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Student fetchById(int id) throws DataException {
    try (Connection conn = DbUtilities.getConnection(); PreparedStatement ps = conn.prepareStatement(FETCH_BY_ID)) {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();

      Student student = null;
      while (rs.next()) {
        student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAddress(rs.getString("address"));
        student.setRoll(rs.getInt("roll"));
      }
      return student;

    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public void update(Student student) throws DataException {
    try (Connection conn = DbUtilities.getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE)) {
      ps.setString(1, student.getName());
      ps.setString(2, student.getAddress());
      ps.setInt(3, student.getRoll());
      ps.setInt(4, student.getId());
      ps.executeUpdate();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public void delete(int id) throws DataException {
    try (Connection conn = DbUtilities.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

}
