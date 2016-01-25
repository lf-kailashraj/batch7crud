package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.lftechnology.batch7crud.constant.EntityConstants.*;
import static com.lftechnology.batch7crud.constant.QueryConstants.*;

/**
 * Created by Pratish Shrestha <pratishshrestha@lftechnology.com> on 1/14/16.
 */
public class EmployeeDao {
  private static final Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName());

  public List<Employee> fetch(int page, int recordLimit) throws DataException {
    try (Connection connection = DbConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(QUERY_SELECT_FROM_EMPLOYEES_LIMIT_OFFSET)) {
      List<Employee> employeeList = new ArrayList<Employee>();
      int offset = (page - 1) * recordLimit;

      stmt.setInt(1, recordLimit);
      stmt.setInt(2, offset);

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        Employee e = new Employee();
        e.setId(rs.getInt(ENTITY_EMPLOYEE_ID));
        e.setFirstName(rs.getString(ENTITY_EMPLOYEE_FIRST_NAME));
        e.setLastName(rs.getString(ENTITY_EMPLOYEE_LAST_NAME));
        e.setStation(rs.getString(ENTITY_EMPLOYEE_STATION));
        employeeList.add(e);
      }
      return employeeList;

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    }
  }

  public Employee fetchById(int id) throws DataException {
    try (Connection connection = DbConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(QUERY_SELECT_FROM_EMPLOYEES_WHERE_ID)) {
      Employee e = null;

      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        e = new Employee();
        e.setId(rs.getInt(ENTITY_EMPLOYEE_ID));
        e.setFirstName(rs.getString(ENTITY_EMPLOYEE_FIRST_NAME));
        e.setLastName(rs.getString(ENTITY_EMPLOYEE_LAST_NAME));
        e.setStation(rs.getString(ENTITY_EMPLOYEE_STATION));
      }
      return e;

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    }
  }

  public void insert(Employee employee) throws DataException {
    try (Connection connection = DbConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(QUERY_INSERT_INTO_EMPLOYEES, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, employee.getFirstName());
      stmt.setString(2, employee.getLastName());
      stmt.setString(3, employee.getStation());

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();

      if (rs.next()) {
        employee.setId(rs.getInt(1));
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    }
  }

  public void update(Employee employee) throws DataException {
    try (Connection connection = DbConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(QUERY_UPDATE_SET_EMPLOYEES)) {
      stmt.setString(1, employee.getFirstName());
      stmt.setString(2, employee.getLastName());
      stmt.setString(3, employee.getStation());
      stmt.setInt(4, employee.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void deleteEmployee(int id) throws DataException {
    try (Connection connection = DbConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(QUERY_DELETE_FROM_EMPLOYEES_WHERE_ID)) {
      stmt.setInt(1, id);
      stmt.executeUpdate();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());

    }
  }

  public int fetchNoOfRecords() throws DataException {
    try (Connection connection = DbConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(QUERY_COUNT_RECORDS_FROM_EMPLOYEES)) {
      int noOfRecords = 0;
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        noOfRecords = rs.getInt(1);
      }

      return noOfRecords;

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

}
