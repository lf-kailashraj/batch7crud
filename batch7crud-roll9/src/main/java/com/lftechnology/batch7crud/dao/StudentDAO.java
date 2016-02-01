package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/14/16.
 */
public class StudentDAO {
  private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

  private static final String UPDATE = "UPDATE tbl_userinfo SET firstname=?, middlename=?, lastname=?, address=?, grade=? WHERE id=?";
  private static final String LIST = "SELECT * FROM tbl_userinfo LIMIT ? OFFSET ?";
  private static final String INSERT = "INSERT INTO tbl_userinfo (firstname,middlename,lastname,address,grade) VALUES (?,?,?,?,?)";
  private static final String SELECT_BY_ID = "SELECT * FROM tbl_userinfo WHERE id=?";
  private static final String DELETE = "DELETE FROM tbl_userinfo WHERE id=?";
  private static final String COUNT_STUDENTS = "SELECT count(*) FROM tbl_userinfo";

  public Student insert(Student student) throws DataException
  {
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt =  conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, student.getFirstName());
      pstmt.setString(2, student.getMiddleName());
      pstmt.setString(3, student.getLastName());
      pstmt.setString(4, student.getAddress());
      pstmt.setInt(5, student.getGrade());
      pstmt.executeUpdate();
      ResultSet resultSet = pstmt.getGeneratedKeys();
      if (resultSet.next()) {
        student.setId(resultSet.getInt(1));
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
    return student;
  }

  public List<Student> fetch(int page, int limit) throws DataException {
    try(Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(LIST)) {
      List<Student> stdList = new ArrayList<>();
      int startOffset = (page - 1) * limit;
      pstmt.setInt(1, limit);
      pstmt.setInt(2, startOffset);

      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        Student std = new Student();
        std.setId(rs.getInt(1));
        std.setFirstName(rs.getString(2));
        std.setMiddleName(rs.getString(3));
        std.setLastName(rs.getString(4));
        std.setAddress(rs.getString(5));
        std.setGrade(rs.getInt(6));
        stdList.add(std);
      }
      return stdList;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }

  public void delete(int id) throws DataException
  {
    try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(DELETE)){
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }

  public Student fetchById(int id) throws DataException {
    try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(SELECT_BY_ID)){
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      Student std = null;
      while (rs.next()) {
        std = new Student();
        std.setId(rs.getInt(1));
        std.setFirstName(rs.getString(2));
        std.setMiddleName(rs.getString(3));
        std.setLastName(rs.getString(4));
        std.setAddress(rs.getString(5));
        std.setGrade(rs.getInt(6));
      }
      return std;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }

  public Student edit(Student student) throws DataException {
    try(Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(UPDATE)) {
      pstmt.setString(1, student.getFirstName());
      pstmt.setString(2, student.getMiddleName());
      pstmt.setString(3, student.getLastName());
      pstmt.setString(4, student.getAddress());
      pstmt.setInt(5, student.getGrade());
      pstmt.setInt(6, student.getId());
      pstmt.executeUpdate();
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
    return student;
  }

  public int studentCount() throws DataException
  {
    try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(COUNT_STUDENTS)) {
      int totalStudents = 0;
      ResultSet rs = pstmt.executeQuery();
      while (rs.next())
        totalStudents = rs.getInt(1);
      return totalStudents;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }
}
