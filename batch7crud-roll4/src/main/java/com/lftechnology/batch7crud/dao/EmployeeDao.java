package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pratishshr on 1/14/16.
 */
public class EmployeeDao {
  private static final Logger LOGGER = Logger.getLogger("EmployeeDaoLog");

  public List<Employee> fetch(int page, int recordLimit) throws DataException {
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      connection = DbConnection.getConnection();
      List<Employee> employeeList = new ArrayList<Employee>();
      int offset = (page - 1) * recordLimit;
      String sql = "SELECT * FROM employees LIMIT ? OFFSET ?";

      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, recordLimit);
      stmt.setInt(2, offset);

      rs = stmt.executeQuery();

      while (rs.next()) {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setFirstName(rs.getString("first_name"));
        e.setLastName(rs.getString("last_name"));
        e.setStation(rs.getString("station"));
        employeeList.add(e);
      }
      return employeeList;

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    } finally {
      try {
        if (rs != null)
          rs.close();
        if (stmt != null)
          stmt.close();
        if (connection != null)
          connection.close();

      } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    }
  }

  public Employee fetchById(int id) throws DataException {
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      connection = DbConnection.getConnection();
      Employee e = null;
      String sql = "SELECT * FROM employees WHERE id=?";
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, id);
      rs = stmt.executeQuery();

      while (rs.next()) {
        e = new Employee();
        e.setId(rs.getInt("id"));
        e.setFirstName(rs.getString("first_name"));
        e.setLastName(rs.getString("last_name"));
        e.setStation(rs.getString("station"));
      }
      return e;

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    } finally {
      try {
        if (rs != null)
          rs.close();
        if (stmt != null)
          stmt.close();
        if (connection != null)
          connection.close();

      } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    }
  }

  public void insert(Employee employee) throws DataException {
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = DbConnection.getConnection();
      String sql = "INSERT INTO employees(first_name, last_name, station) VALUES(?,?,?)";
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, employee.getFirstName());
      stmt.setString(2, employee.getLastName());
      stmt.setString(3, employee.getStation());

      stmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    } finally {
      try {
        if (stmt != null)
          stmt.close();
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    }
  }

  public void update(Employee employee) throws DataException {
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      connection = DbConnection.getConnection();
      String sql = "UPDATE employees SET first_name=?, last_name=?, station=? WHERE id=?";
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, employee.getFirstName());
      stmt.setString(2, employee.getLastName());
      stmt.setString(3, employee.getStation());
      stmt.setInt(4, employee.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    } finally {
      try {
        if (stmt != null)
          stmt.close();
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    }
  }

  public void deleteEmployee(int id) throws DataException {
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      connection = DbConnection.getConnection();
      String sql = "DELETE FROM employees WHERE id=?";

      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, id);
      stmt.executeUpdate();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    } finally {
      try {
        if (rs != null)
          rs.close();
        if (stmt != null)
          stmt.close();
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    }
  }

  public int fetchNoOfRecords() throws DataException {
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      connection = DbConnection.getConnection();
      int noOfRecords = 0;
      String sql = "SELECT COUNT(*) FROM employees";
      stmt = connection.prepareStatement(sql);
      rs = stmt.executeQuery();

      while (rs.next()) {
        noOfRecords = rs.getInt(1);
      }
      return noOfRecords;

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    } finally {
      try {
        if (rs != null)
          rs.close();
        if (stmt != null)
          stmt.close();
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    }
  }

}
