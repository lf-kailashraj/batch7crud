package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */

public class EmployeeDao {
  private static final Logger LOGGER = Logger.getLogger(Employee.class.getName());

  public void create(Employee employee) throws DataException {
    String sql = "INSERT INTO employee (name, address, designation, phone) VALUES (?,?,?,?)";
    try(Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(sql)
    ){
      statement.setString(1, employee.getName());
      statement.setString(2, employee.getAddress());
      statement.setString(3, employee.getDesignation());
      statement.setString(4, employee.getPhone());
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public List<Employee> fetch(Integer pageLimit, Integer offset) throws DataException {
    List<Employee> employeeList = new ArrayList<>();
    String sql = "SELECT * FROM employee order by id limit ? offset ?";

    try (Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)
    ){
      statement.setInt(1, pageLimit);
      statement.setInt(2, offset);
      ResultSet result = statement.executeQuery();
      while (result.next()) {
        Employee employee = new Employee();
        employee.setId(result.getInt("id"));
        employee.setName(result.getString("name"));
        employee.setAddress(result.getString("address"));
        employee.setDesignation(result.getString("designation"));
        employee.setPhone(result.getString("phone"));
        employeeList.add(employee);
      }
      return employeeList;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Employee fetchById(Integer id) throws DataException {
    Employee employee = null;
    String sql = "SELECT * FROM employee where id = ?";
    try (Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(sql)
    ){
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      while (result.next()) {
        employee = new Employee();
        employee.setId(result.getInt("id"));
        employee.setName(result.getString("name"));
        employee.setAddress(result.getString("address"));
        employee.setDesignation(result.getString("designation"));
        employee.setPhone(result.getString("phone"));
      }
      return employee;
    }
    catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void edit(Employee employee) throws DataException {
    String sql = "update employee set name = ?, address = ?, designation = ?, phone = ? where id = ?";

    try (Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(sql)
    ){
      statement.setString(1, employee.getName());
      statement.setString(2, employee.getAddress());
      statement.setString(3, employee.getDesignation());
      statement.setString(4, employee.getPhone());
      statement.setInt(5, employee.getId());
      statement.executeUpdate();
    }
    catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void delete(Integer id) throws DataException {
    String sql = "delete from employee where id = ?";
    try (Connection conn = DBConnection.getConnection();
      PreparedStatement statement = conn.prepareStatement(sql)
    ) {
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Integer count() throws DataException {
    String sql = "select count(*) as total from employee";
    try(Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)
    ){
      ResultSet result = statement.executeQuery();
      if (result.next()){
        return result.getInt("total");
      }
      else {
        return 0;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }
}
