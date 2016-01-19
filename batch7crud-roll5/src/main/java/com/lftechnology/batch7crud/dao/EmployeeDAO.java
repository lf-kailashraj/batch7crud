package com.lftechnology.batch7crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

public class EmployeeDAO {

    public List<Employee> fetch() throws DataException {
        try {
            List<Employee> empList = new ArrayList<Employee>();
            Employee emp = null;
            String sql = "SELECT * FROM employee LIMIT 20";

            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                emp = new Employee();
                emp.setId(result.getInt("id"));
                emp.setFirstName(result.getString("first_name"));
                emp.setLastName(result.getString("last_name"));
                emp.setDepartment(result.getString("department"));
                emp.setAddress(result.getString("address"));
                empList.add(emp);

            }

            return empList;

        } catch (SQLException e) {
            throw new DataException();
        }

    }

    public Employee fetchById(int id) throws DataException {
        try {
            Employee emp = null;
            String sql = "SELECT * FROM employee where id = ?";

            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setDepartment(rs.getString("department"));
                emp.setAddress(rs.getString("address"));

            }

            return emp;

        } catch (SQLException e) {
            throw new DataException();
        }

    }

    public void deleteById(int id) throws DataException {
        try {
            String sql = "DELETE FROM employee WHERE id = ?";

            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataException();
        }

    }

    public boolean create(Employee employee) throws DataException {
        try {
            String sql = "INSERT INTO employee (first_name, last_name, department, address) VALUES(?, ?, ?, ?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getDepartment());
            ps.setString(4, employee.getAddress());

            if (ps.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new DataException();
        }

    }

    public void edit(Employee employee, int id) throws DataException {
        try {
            String sql = "UPDATE employee SET first_name = ?, last_name = ?, department = ?, address = ? WHERE id = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getDepartment());
            ps.setString(4, employee.getAddress());
            ps.setInt(5, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataException();
        }

    }
}
