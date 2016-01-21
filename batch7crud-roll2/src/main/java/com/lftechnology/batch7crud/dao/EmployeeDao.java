package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.constants.EntityConstants;
import com.lftechnology.batch7crud.constants.QueryConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
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
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
public class EmployeeDao {
  private static final Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName());

  public void insert(Employee employee) throws DataException {
    try (Connection con = DBConnection.getSqlConnection();
            PreparedStatement preStmt = con.prepareStatement(QueryConstants.EMPLOYEE_INSERT)) {
      preStmt.setString(1, employee.getName());
      preStmt.setString(2, employee.getAddress());
      preStmt.setString(3, employee.getEmail());
      preStmt.setString(4, employee.getContact());
      preStmt.execute();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public List<Employee> fetch(int noOfRecordsPerPage, int page) throws DataException {
    try (Connection con = DBConnection.getSqlConnection();
            PreparedStatement preStmt = con.prepareStatement(QueryConstants.EMPLOYEE_SELECT_LIMIT_OFFSET)) {
      List<Employee> empList = new ArrayList<Employee>();
      preStmt.setInt(1, noOfRecordsPerPage);
      preStmt.setInt(2, page);
      ResultSet resultSet = preStmt.executeQuery();
      while (resultSet.next()) {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt(EntityConstants.ID));
        employee.setName(resultSet.getString(EntityConstants.USERNAME));
        employee.setAddress(resultSet.getString(EntityConstants.ADDRESS));
        employee.setEmail(resultSet.getString(EntityConstants.EMAIL));
        employee.setContact(resultSet.getString(EntityConstants.CONTACT));
        empList.add(employee);
      }
      return empList;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Employee fetchById(int id) throws DataException {
    try (Connection con = DBConnection.getSqlConnection();
            PreparedStatement preStmt = con.prepareStatement(QueryConstants.EMPLOYEE_SELECT_BY_ID)) {
      Employee employee = null;
      preStmt.setInt(1, id);
      ResultSet resultSet = preStmt.executeQuery();
      while (resultSet.next()) {
        employee = new Employee();
        employee.setId(resultSet.getInt(EntityConstants.ID));
        employee.setName(resultSet.getString(EntityConstants.USERNAME));
        employee.setAddress(resultSet.getString(EntityConstants.ADDRESS));
        employee.setEmail(resultSet.getString(EntityConstants.EMAIL));
        employee.setContact(resultSet.getString(EntityConstants.CONTACT));
      }
      return employee;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void update(Employee employee) throws DataException {
    try (Connection con = DBConnection.getSqlConnection();
            PreparedStatement preStmt = con.prepareStatement(QueryConstants.EMPLOYEE_UPDATE_BY_ID)) {
      preStmt.setString(1, employee.getName());
      preStmt.setString(2, employee.getAddress());
      preStmt.setString(3, employee.getEmail());
      preStmt.setString(4, employee.getContact());
      preStmt.setInt(5, employee.getId());
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void delete(int empId) throws DataException {
    try (Connection con = DBConnection.getSqlConnection();
            PreparedStatement preStmt = con.prepareStatement(QueryConstants.EMPLOYEE_DELETE_BY_ID)) {
      preStmt.setInt(1, empId);
      preStmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public int getTotalNoOfRecords() throws DataException {
    try (Connection con = DBConnection.getSqlConnection();
            PreparedStatement preStmt = con.prepareStatement(QueryConstants.EMPLOYEE_SELECT_COUNT)) {
      ResultSet resultSet = preStmt.executeQuery();
      int total = 0;
      while (resultSet.next()) {
        total = resultSet.getInt(EntityConstants.TOTAL);
      }
      return total;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }
}
