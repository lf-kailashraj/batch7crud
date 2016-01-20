package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.util.DBConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
public class EmployeeDao {
  private static final Logger LOGGER = Logger.getLogger("employeeDaoLogger");
  private Connection con;

  public void insert(Employee employee) throws DataException {
    try {
      con = DBConnection.getSqlConnection();
      String qry = "INSERT INTO employee (username,address,email,contact) VALUES (?,?,?,?)";
      PreparedStatement preStmt = con.prepareStatement(qry);
      preStmt.setString(1, employee.getName());
      preStmt.setString(2, employee.getAddress());
      preStmt.setString(3, employee.getEmail());
      preStmt.setString(4, employee.getContact());
      preStmt.execute();
      preStmt.close();
      con.close();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public List<Employee> fetch(int noOfRecordsPerPage, int page) throws DataException {
    try {
      con = DBConnection.getSqlConnection();
      List<Employee> empList = new ArrayList<Employee>();
      String qry = "SELECT * FROM employee LIMIT ? OFFSET ?";
      PreparedStatement preStmt = con.prepareStatement(qry);
      preStmt.setInt(1, noOfRecordsPerPage);
      preStmt.setInt(2, page);
      ResultSet resultSet = preStmt.executeQuery();
      while (resultSet.next()) {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("username"));
        employee.setAddress(resultSet.getString("address"));
        employee.setEmail(resultSet.getString("email"));
        employee.setContact(resultSet.getString("contact"));
        empList.add(employee);
      }
      preStmt.close();
      con.close();
      return empList;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Employee fetchById(int id) throws DataException {
    try {
      con = DBConnection.getSqlConnection();
      Employee employee = null;
      String qry = "SELECT * FROM employee WHERE id=?";
      PreparedStatement preStmt = con.prepareStatement(qry);
      preStmt.setInt(1, id);
      ResultSet resultSet = preStmt.executeQuery();
      while (resultSet.next()) {
        employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("username"));
        employee.setAddress(resultSet.getString("address"));
        employee.setEmail(resultSet.getString("email"));
        employee.setContact(resultSet.getString("contact"));
      }
      preStmt.close();
      con.close();
      return employee;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void update(Employee employee) throws DataException {
    try {
      con = DBConnection.getSqlConnection();
      String qry = "UPDATE employee SET username=?, address=?, email=?, contact=? WHERE id=?";
      PreparedStatement preStmt = con.prepareStatement(qry);
      preStmt.setString(1, employee.getName());
      preStmt.setString(2, employee.getAddress());
      preStmt.setString(3, employee.getEmail());
      preStmt.setString(4, employee.getContact());
      preStmt.setInt(5, employee.getId());
      preStmt.execute();
      preStmt.close();
      con.close();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void delete(int empId) throws DataException {
    try {
      con = DBConnection.getSqlConnection();
      String qry = "DELETE FROM employee where id=?";
      PreparedStatement preStmt = con.prepareStatement(qry);
      preStmt.setInt(1, empId);
      preStmt.executeUpdate();
      preStmt.close();
      con.close();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public int getTotalNoOfRecords() throws DataException {
    try {
      con = DBConnection.getSqlConnection();
      String qry = "SELECT COUNT(*) AS total FROM employee";
      PreparedStatement preStmt = con.prepareStatement(qry);
      ResultSet resultSet = preStmt.executeQuery();
      int total = 0;
      while (resultSet.next()) {
        total = resultSet.getInt("total");
      }
      preStmt.close();
      con.close();
      return total;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }
}
