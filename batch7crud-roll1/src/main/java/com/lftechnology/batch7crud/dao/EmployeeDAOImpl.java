package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.utils.DbUtils;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.entity.Employee;

import static com.lftechnology.batch7crud.constant.EntityConstant.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EmployeeDAO perfoms CRUD operation in employee table
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/18/16
 */

public class EmployeeDAOImpl implements EmployeeDAO {

  private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class.getName());

  @Override
  public void insert(Employee employee) throws DataException {

    String sqlQuery = "INSERT INTO employee(username,password,fullname,department,address,age) VALUES(?,?,?,?,?,?)";
    try (Connection connection = DbUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)
    ) {
      ps.setString(1, employee.getUserName());
      ps.setString(2, employee.getPassword());
      ps.setString(3, employee.getFullName());
      ps.setString(4, employee.getDepartment());
      ps.setString(5, employee.getAddress());
      ps.setInt(6, employee.getAge());
      ps.executeUpdate();
      ResultSet resultSet = ps.getGeneratedKeys();
      if (resultSet.next()) {
        employee.setId(resultSet.getInt(1));
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  @Override
  public List<Employee> fetch(int offset, int limit) throws DataException {

    String sqlQuery = "SELECT * FROM employee ORDER BY id LIMIT ? OFFSET ?";
    try (Connection connection = DbUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery)
    ) {

      ps.setInt(1, limit);
      ps.setInt(2, offset);
      ResultSet result = ps.executeQuery();
      List<Employee> employees = new ArrayList<>();

      while (result.next()) {
        Employee employee = new Employee();
        employee.setId(result.getInt(ID));
        employee.setUserName(result.getString(USER_NAME));
        employee.setPassword(result.getString(PASSWORD));
        employee.setFullName(result.getString(FULL_NAME));
        employee.setAddress(result.getString(ADDRESS));
        employee.setDepartment(result.getString(DEPARTMENT));
        employee.setAge(result.getInt(AGE));
        employees.add(employee);
      }
      return employees;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  @Override
  public Employee fetchById(int id) throws DataException {

    String sqlQuery = "SELECT * FROM employee WHERE id = ?";
    try (Connection connection = DbUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
      ps.setInt(1, id);
      ResultSet result = ps.executeQuery();
      Employee employee = null;
      while (result.next()) {
        employee = new Employee();
        employee.setId(result.getInt(ID));
        employee.setUserName(result.getString(USER_NAME));
        employee.setPassword(result.getString(PASSWORD));
        employee.setFullName(result.getString(FULL_NAME));
        employee.setAddress(result.getString(ADDRESS));
        employee.setDepartment(result.getString(DEPARTMENT));
        employee.setAge(result.getInt(AGE));
      }
      return employee;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  @Override
  public void update(Employee employee) throws DataException {

    String sqlQuery = "UPDATE employee SET userName=?, password=?,fullName=?,department=?,address=?,age=? WHERE id=?"; //NOSONAR
    try (Connection connection = DbUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
      ps.setString(1, employee.getUserName());
      ps.setString(2, employee.getPassword());
      ps.setString(3, employee.getFullName());
      ps.setString(4, employee.getDepartment());
      ps.setString(5, employee.getAddress());
      ps.setInt(6, employee.getAge());
      ps.setInt(7, employee.getId());
      ps.executeUpdate();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  @Override
  public void delete(int id) throws DataException {

    String sqlQuery = "DELETE FROM employee WHERE id = ?";
    try (Connection connection = DbUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
      ps.setInt(1, id);
      ps.executeUpdate();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  @Override
  public Integer fetchNoOfRecords() throws DataException {

    String sqlQuery = "SELECT count(*) AS total FROM employee";
    try (Connection connection = DbUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
      ResultSet result = ps.executeQuery();
      if (result.next()) {
        return result.getInt("total");
      } else {
        return null;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }
}
