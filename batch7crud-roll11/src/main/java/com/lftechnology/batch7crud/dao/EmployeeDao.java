package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.constant.EmployeeConstants;
import com.lftechnology.batch7crud.database.DbConnector;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by achyut on 1/26/16.
 */
public class EmployeeDao {
  private static final String GET_ALL_EMPLOYEES = "SELECT * from employee_details";
  private static final String INSERT_EMPLOYEE = "INSERT INTO employee_details (name, address, department, position, salary) VALUES (?,?,?,?,?)";
  private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee_details WHERE emp_id = ?";
  private static final String UPDATE_EMPLOYEE = "UPDATE employee_details SET name = ?, address = ?, department = ?, position = ?,salary = ? WHERE emp_id=?";
  private static final String DELETE_EMPLOYEE = "DELETE FROM employee_details WHERE emp_id = ?";

  private static final Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName());


  public List<Employee> getAllEmployees() throws DataException {
    List<Employee> employeeList = new ArrayList<>();
    try (Connection con = DbConnector.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ALL_EMPLOYEES)) {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Employee emp = new Employee();
        emp.setEmpId(rs.getInt(EmployeeConstants.EMP_ID));
        emp.setName(rs.getString(EmployeeConstants.NAME));
        emp.setAddress(rs.getString(EmployeeConstants.ADDRESS));
        emp.setDepartment(rs.getString(EmployeeConstants.DEPARTMENT));
        emp.setPosition(rs.getString(EmployeeConstants.POSITION));
        emp.setSalary(rs.getInt(EmployeeConstants.SALARY));
        employeeList.add(emp);
      }
      return employeeList;
    } catch (SQLException | NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void create(Employee employee) throws DataException {
    try (Connection con = DbConnector.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT_EMPLOYEE)) {
      ps.setString(1, employee.getName());
      ps.setString(2, employee.getAddress());
      ps.setString(3, employee.getDepartment());
      ps.setString(4, employee.getPosition());
      ps.setInt(5, employee.getSalary());
      ps.execute();
    } catch (SQLException | NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Employee getEmployeeById(int id) throws DataException {
    try (Connection con = DbConnector.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
      Employee employee = null;
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        employee = new Employee();
        employee.setEmpId(rs.getInt(EmployeeConstants.EMP_ID));
        employee.setName(rs.getString(EmployeeConstants.NAME));
        employee.setAddress(rs.getString(EmployeeConstants.ADDRESS));
        employee.setDepartment(rs.getString(EmployeeConstants.DEPARTMENT));
        employee.setPosition(rs.getString(EmployeeConstants.POSITION));
        employee.setSalary(rs.getInt(EmployeeConstants.SALARY));
      }
      return employee;
    } catch (SQLException | NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void update(Employee employee) throws DataException {
    try (Connection con = DbConnector.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_EMPLOYEE)) {
      ps.setString(1, employee.getName());
      ps.setString(2, employee.getAddress());
      ps.setString(3, employee.getDepartment());
      ps.setString(4, employee.getPosition());
      ps.setInt(5, employee.getSalary());
      ps.setInt(6, employee.getEmpId());
      ps.executeUpdate();
    } catch (SQLException | NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void delete(int id) throws DataException {
    try (Connection con = DbConnector.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_EMPLOYEE)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException | NamingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }
}
