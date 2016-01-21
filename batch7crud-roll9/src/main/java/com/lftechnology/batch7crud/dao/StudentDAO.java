package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.constants.Query;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/14/16.
 */
public class StudentDAO {
  private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

  public void insert(Student s) throws DataException//get the object
  {
    try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(Query.INSERT);) {
      pstmt.setString(1, s.getFirstName());
      pstmt.setString(2, s.getMiddleName());
      pstmt.setString(3, s.getLastName());
      pstmt.setString(4, s.getAddress());
      pstmt.setInt(5, s.getGrade());
      pstmt.executeUpdate();
      conn.close();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public List<Student> fetch(int page, int limit) throws DataException {
    try(Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(Query.LIST)) {
      List<Student> stdList = new ArrayList<Student>();
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
      conn.close();
      return stdList;
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public void delete(int id) throws DataException//get the object
  {
    try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(Query.DELETE)){
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public Student fetchById(int id) throws DataException {
    try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(Query.SELECT_BY_ID)){
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
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public void edit(Student s) throws DataException {
    try(Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(Query.UPDATE)) {
      pstmt.setString(1, s.getFirstName());
      pstmt.setString(2, s.getMiddleName());
      pstmt.setString(3, s.getLastName());
      pstmt.setString(4, s.getAddress());
      pstmt.setInt(5, s.getGrade());
      pstmt.setInt(6, s.getId());
      pstmt.executeUpdate();
      conn.close();
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public int studentCount() throws DataException//get the object
  {
    try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(Query.COUNT_STUDENTS)) {
      int totalStudents = 0;
      ResultSet rs = pstmt.executeQuery();
      while (rs.next())
        totalStudents = rs.getInt(1);
      pstmt.close();
      conn.close();
      return totalStudents;
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }
}
