package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DbUtilities;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leapfrog on 1/18/16.
 */
public class StudentDataAccess {
  private static final Logger LOGGER = Logger.getLogger(StudentDataAccess.class.getName());
  PreparedStatement ps = null;

  public void addNew(Student s) throws DataException {
    try (Connection conn = DbUtilities.getConncetion()) {
      String query = "insert into students (name,address,roll) values (?,?,?);";
      ps = conn.prepareStatement(query);
      ps.setString(1, s.getName());
      ps.setString(2, s.getAddress());
      ps.setInt(3, s.getRoll());
      ps.executeUpdate();
      ps.close();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  public List<Student> fetch(int page, int pageSize) throws DataException {
    try (Connection conn = DbUtilities.getConncetion()) {
      List<Student> studentList = new ArrayList<Student>();
      String query = "select * from students limit ? offset ?";
      ps = conn.prepareStatement(query);
      ps.setInt(1, pageSize);
      ps.setInt(2, (page - 1) * pageSize);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Student s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        studentList.add(s);
      }
      rs.close();
      ps.close();
      conn.close();
      return studentList;

    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public int fetchTotal() throws DataException {
    try (Connection conn = DbUtilities.getConncetion()) {
      String query = "SELECT COUNT(*) AS total FROM students";
      int totalSize = 0;
      ps = conn.prepareStatement(query);

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
    try (Connection conn = DbUtilities.getConncetion()) {
      String query = "select * from students where id = ?";
      ps = conn.prepareStatement(query);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();

      Student s = null;
      while (rs.next()) {
        s = new Student();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setAddress(rs.getString("address"));
        s.setRoll(rs.getInt("roll"));
      }
      rs.close();
      ps.close();
      conn.close();
      return s;

    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

  public void update(Student s, int id) throws DataException {
    try (Connection conn = DbUtilities.getConncetion()) {
      String query = "update students set name = ?, address = ?, roll = ? where id = ?";
      ps = conn.prepareStatement(query);
      ps.setString(1, s.getName());
      ps.setString(2, s.getAddress());
      ps.setInt(3, s.getRoll());
      ps.setInt(4, id);
      ps.executeUpdate();
      ps.close();
      conn.close();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  public void delete(int roll) throws DataException {
    try (Connection conn = DbUtilities.getConncetion()) {
      String query = "delete from students where roll = ?";
      ps = conn.prepareStatement(query);
      ps.setInt(1, roll);
      ps.executeUpdate();
      ps.close();
      conn.close();

    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new DataException();
    }
  }

}
