package com.lftechnology.batch7crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.db.ReleaseResource;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

public class EmployeeDAO extends ReleaseResource {
    private PreparedStatement ps = null;
    private Connection conn = null;
    private ResultSet rs = null;

    private static final Logger LOGGER = Logger.getLogger("EmployeeDAOLog");

    public List<Employee> fetch(int pageNo) throws DataException {
        List<Employee> empList = new ArrayList<Employee>();
        Employee emp = null;

        try {
            String sql = "SELECT * FROM employee LIMIT 10 OFFSET ?";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pageNo * 10);

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
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            closeConnection(null, conn, ps);
        }

    }

    public Employee fetchById(int id) throws DataException {
        try {
            Employee emp = null;
            String sql = "SELECT * FROM employee where id = ?";

            conn = DBConnection.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

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
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            closeConnection(rs, conn, ps);
        }

    }

    public void deleteById(int id) throws DataException {
        try {
            String sql = "DELETE FROM employee WHERE id = ?";

            conn = DBConnection.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            closeConnection(null, conn, ps);
        }

    }

    public void create(Employee employee) throws DataException {
        try {
            String sql = "INSERT INTO employee (first_name, last_name, department, address) VALUES(?, ?, ?, ?)";
            conn = DBConnection.getConnection();

            ps = conn.prepareStatement(sql);

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getDepartment());
            ps.setString(4, employee.getAddress());

            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            closeConnection(null, conn, ps);
        }

    }

    public void edit(Employee employee, int id) throws DataException {
        try {
            String sql = "UPDATE employee SET first_name = ?, last_name = ?, department = ?, address = ? WHERE id = ?";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getDepartment());
            ps.setString(4, employee.getAddress());
            ps.setInt(5, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            closeConnection(null, conn, ps);
        }

    }

    public int count() throws DataException {
        try {
            String sql = "SELECT COUNT(*) FROM employee";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            closeConnection(rs, conn, ps);
        }

    }
}
