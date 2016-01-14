package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pratishshr on 1/14/16.
 */
public class EmployeeDAO {
    private DBConnection connection;

    public EmployeeDAO() throws ClassNotFoundException {
        connection = new DBConnection();
    }

    public List<Employee> getAll() throws SQLException {
        List<Employee> employeeList = new ArrayList<Employee>();

        String sql = "SELECT * FROM employees";
        connection.open();
        ResultSet rs = connection.executeQuery(sql);
        while(rs.next()) {
            Employee e = new Employee();

            e.setId(rs.getInt("id"));
            e.setFirstName(rs.getString("first_name"));
            e.setLastName(rs.getString("last_name"));
            e.setStation(rs.getString("station"));
            employeeList.add(e);
        }
        connection.close();
        return employeeList;

    }

    public void save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees(first_name, last_name, station) VALUES(?,?,?)";
        connection.open();
        PreparedStatement stmt = connection.initStatement(sql);

        stmt.setString(1, employee.getFirstName());
        stmt.setString(2, employee.getLastName());
        stmt.setString(3, employee.getStation());

        connection.executeUpdate();
        connection.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        connection.open();
        PreparedStatement stmt = connection.initStatement(sql);
        stmt.setInt(1, id);

        connection.executeUpdate(sql);
        connection.close();
    }



}
