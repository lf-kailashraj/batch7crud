package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.constants.ModelConstants;
import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */

public class EmployeeDao {
  private static final Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName());

  private static final String EMPLOYEE_INSERT = "INSERT INTO employee (name, address, designation, phone) VALUES (?,?,?,?)";
  private static final String EMPLOYEE_SELECT_ALL = "SELECT * FROM employee order by id limit ? offset ?";
  private static final String EMPLOYEE_SELECT_BY_ID = "SELECT * FROM employee where id = ?";
  private static final String EMPLOYEE_UPDATE_BY_ID = "update employee set name = ?, address = ?, designation = ?, phone = ? where id = ?";
  private static final String EMPLOYEE_DELETE_BY_ID = "delete from employee where id = ?";
  private static final String EMPLOYEE_GET_COUNT = "select count(*) as total from employee";

  public Employee create(Employee employee) throws DataException {
    ResultSet resultSet = null;
    try(Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(EMPLOYEE_INSERT, Statement.RETURN_GENERATED_KEYS)
    ){
      statement.setString(1, employee.getName());
      statement.setString(2, employee.getAddress());
      statement.setString(3, employee.getDesignation());
      statement.setString(4, employee.getPhone());
      statement.executeUpdate();
      resultSet = statement.getGeneratedKeys();
      if (resultSet.next()) {
        employee.setId(resultSet.getInt(1));
      }
      return employee;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
    finally {
      try {
        if (resultSet != null)
          resultSet.close();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
        throw new DataException(e.getMessage());
      }
    }
  }

  public List<Employee> fetch(Integer pageLimit, Integer offset) throws DataException {
    List<Employee> employeeList = new ArrayList<>();
    ResultSet resultSet = null;

    try (Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(EMPLOYEE_SELECT_ALL)
    ){
      statement.setInt(1, pageLimit);
      statement.setInt(2, offset);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt(ModelConstants.ID));
        employee.setName(resultSet.getString(ModelConstants.NAME));
        employee.setAddress(resultSet.getString(ModelConstants.ADDRESS));
        employee.setDesignation(resultSet.getString(ModelConstants.DESIGNATION));
        employee.setPhone(resultSet.getString(ModelConstants.PHONE));
        employeeList.add(employee);
      }
      return employeeList;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
    finally {
      try {
        if (resultSet != null)
          resultSet.close();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
        throw new DataException(e.getMessage());
      }
    }
  }

  public Employee fetchById(Integer id) throws DataException {
    Employee employee = null;
    ResultSet resultSet = null;

    try (Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(EMPLOYEE_SELECT_BY_ID)
    ){
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        employee = new Employee();
        employee.setId(resultSet.getInt(ModelConstants.ID));
        employee.setName(resultSet.getString(ModelConstants.NAME));
        employee.setAddress(resultSet.getString(ModelConstants.ADDRESS));
        employee.setDesignation(resultSet.getString(ModelConstants.DESIGNATION));
        employee.setPhone(resultSet.getString(ModelConstants.PHONE));
      }
      return employee;
    }
    catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
    finally {
      try {
        if (resultSet != null)
          resultSet.close();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
        throw new DataException(e.getMessage());
      }
    }
  }

  public Employee edit(Employee employee) throws DataException {
    try (Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(EMPLOYEE_UPDATE_BY_ID)
    ){
      statement.setString(1, employee.getName());
      statement.setString(2, employee.getAddress());
      statement.setString(3, employee.getDesignation());
      statement.setString(4, employee.getPhone());
      statement.setInt(5, employee.getId());
      statement.executeUpdate();
      return employee;
    }
    catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void delete(Integer id) throws DataException {
    try (Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(EMPLOYEE_DELETE_BY_ID)
    ) {
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Integer count() throws DataException {
    ResultSet resultSet = null;
    try(Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(EMPLOYEE_GET_COUNT)
    ){
      resultSet = statement.executeQuery();
      if (resultSet.next()){
        return resultSet.getInt(ModelConstants.TOTAL);
      }
      else {
        return 0;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
    finally {
      try {
        if (resultSet != null)
          resultSet.close();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
        throw new DataException(e.getMessage());
      }
    }
  }
}
