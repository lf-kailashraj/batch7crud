package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishma on 1/19/16.
 */
public class EmployeeDao {

    public void create(Employee employee) throws DataException {
        Statement stmt = null;

        try {
            String sql = "INSERT INTO employee (name, address, designation, phone) VALUES (?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getAddress());
            statement.setString(3, employee.getDesignation());
            statement.setString(4, employee.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException();
        }
    }

    public List<Employee> fetch() throws DataException {
        try {
            List<Employee> empList = new ArrayList<Employee>();
            String sql = "SELECT * FROM employee";
            Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Employee emp = new Employee();
                emp.setId(result.getInt("id"));
                emp.setName(result.getString("name"));
                emp.setAddress(result.getString("address"));
                emp.setDesignation(result.getString("designation"));
                emp.setPhone(result.getString("phone"));
                empList.add(emp);
            }
            return empList;
        } catch (SQLException e) {
            throw new DataException();
        } catch (DataException e) {
            throw new DataException();
        }
    }
}
