package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.util.DbConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pratishshr on 1/14/16.
 */
public class EmployeeDao {
    private Connection connection;

    public EmployeeDao() throws DataException {
        connection = DbConnection.getConnection();
    }

    public List<Employee> fetch(int page, int recordLimit) throws DataException {
        try {
            List<Employee> employeeList = new ArrayList<Employee>();
            int offset = (page - 1) * recordLimit;
            String sql = "SELECT * FROM employees LIMIT ? OFFSET ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, recordLimit);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();

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
            throw new DataException();
        }
    }

    public Employee fetchById(int id) throws DataException {
        try {
            Employee e = null;
            String sql = "SELECT * FROM employees WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                e = new Employee();
                e.setId(rs.getInt("id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setStation(rs.getString("station"));
            }
            return e;

        } catch (SQLException e) {
            throw new DataException();
        }
    }

    public void insert(Employee employee) throws DataException {
        try {
            String sql = "INSERT INTO employees(first_name, last_name, station) VALUES(?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getStation());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataException();
        }
    }

    public void update(Employee employee) throws DataException {
        try {
            String sql = "UPDATE employees SET first_name=?, last_name=?, station=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getStation());
            stmt.setInt(4, employee.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataException();
        }
    }

    public void deleteEmployee(int id) throws DataException {
        try {
            String sql = "DELETE FROM employees WHERE id=?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataException();
        }
    }

    public int fetchNoOfRecords() throws DataException {
        try {
            int noOfRecords = 0;
            String sql = "SELECT COUNT(*) FROM employees";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
            return noOfRecords;

        } catch (SQLException e) {
            throw new DataException("Error in count");
        }
    }

}
