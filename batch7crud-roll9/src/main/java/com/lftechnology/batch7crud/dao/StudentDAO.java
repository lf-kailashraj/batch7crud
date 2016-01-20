package com.lftechnology.batch7crud.dao;

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
  private static final Logger LOGGER = Logger.getLogger("appLogger");


  public void insert(Student s) throws DataException//get the object
  {
    Connection conn = null;
    PreparedStatement pstmt=null;
    try {
      conn =  DBConnection.getConnection();
      pstmt = conn.prepareStatement("INSERT INTO tbl_userinfo (firstname,middlename,lastname,address,grade) VALUES (?,?,?,?,?);");
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
    } finally {
      DBConnection.closePreparedStatement();
      DBConnection.closeConnection(conn);
    }
  }

  public List<Student> fetch(int page, int limit) throws DataException {

    Connection conn = null;
    PreparedStatement pstmt=null;
    ResultSet rs = null;
    try {
      conn = DBConnection.getConnection();
      List<Student> stdList = new ArrayList<Student>();
      int startOffset = (page - 1) * limit;
      pstmt = conn.prepareStatement("SELECT * FROM tbl_userinfo LIMIT ? OFFSET ?");
      pstmt.setInt(1, limit);
      pstmt.setInt(2, startOffset);
      rs = pstmt.executeQuery();
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
    } finally {
      DBConnection.closeResultSet(rs);
      DBConnection.closePreparedStatement(pstmt);
      DBConnection.closeConnection(conn);
    }
  }

  public void delete(int id) throws DataException//get the object
  {
    Connection conn = null;
    PreparedStatement pstmt=null;
    try {
      conn = DBConnection.getConnection();
      pstmt = conn.prepareStatement("DELETE FROM tbl_userinfo WHERE id=?");
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    } finally {
      DBConnection.closePreparedStatement(pstmt);
      DBConnection.closeConnection(conn);
    }
  }

  public Student fetchById(int id) throws DataException {
    Connection conn = null;
    PreparedStatement pstmt=null;
    ResultSet rs = null;
    try {
      conn = DBConnection.getConnection();
      Student std = null;
      pstmt = conn.prepareStatement("SELECT * FROM tbl_userinfo WHERE id=?");
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();
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
    } finally {
      DBConnection.closeResultSet(rs);
      DBConnection.closePreparedStatement(pstmt);
      DBConnection.closeConnection(conn);
    }
  }

  public void edit(Student s) throws DataException {
    Connection conn = null;
    PreparedStatement pstmt=null;
    try {
      conn = DBConnection.getConnection();
      pstmt = conn.prepareStatement("UPDATE tbl_userinfo SET firstname=?, middlename=?, lastname=?, address=?, grade=? WHERE id=?");
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
    } finally {
      DBConnection.closePreparedStatement(pstmt);
      DBConnection.closeConnection(conn);
    }
  }

  public int studentCount() throws DataException//get the object
  {
    Connection conn = null;
    PreparedStatement pstmt=null;
    ResultSet rs = null;
    try {
      int totalStudents = 0;
      conn = DBConnection.getConnection();
      String query = "SELECT count(*) FROM tbl_userinfo";
      pstmt = conn.prepareStatement(query);
      rs = pstmt.executeQuery();
      while (rs.next())
        totalStudents = rs.getInt(1);
      pstmt.close();
      conn.close();

      return totalStudents;
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    } finally {
      DBConnection.closeResultSet(rs);
      DBConnection.closePreparedStatement(pstmt);
      DBConnection.closeConnection(conn);
    }
  }
}
