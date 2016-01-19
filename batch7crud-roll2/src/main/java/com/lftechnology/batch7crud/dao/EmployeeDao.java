package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.util.DBConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romit on 1/14/16.
 */
public class EmployeeDao {
    private Connection con;

    public void insert(Employee employee) throws DataException {
        try {
            con = DBConnection.getSqlConnection();
            String qry = "INSERT INTO employee (username,address,email,contact) VALUES (?,?,?,?)";
            PreparedStatement preStmt = con.prepareStatement(qry);
            preStmt.setString(1, employee.getName());
            preStmt.setString(2, employee.getAddress());
            preStmt.setString(3, employee.getEmail());
            preStmt.setString(4, employee.getContact());
            preStmt.execute();
            con.close();
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    public List<Employee> fetch(int page) throws DataException {
        try {
            con = DBConnection.getSqlConnection();
            List<Employee> empList = new ArrayList<Employee>();
            page = page * 10;
            String qry = "SELECT * FROM employee LIMIT 10 OFFSET ?";
            PreparedStatement preStmt = con.prepareStatement(qry);
            preStmt.setInt(1, page);
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("username"));
                employee.setAddress(resultSet.getString("address"));
                employee.setEmail(resultSet.getString("email"));
                employee.setContact(resultSet.getString("contact"));

                empList.add(employee);
            }
            con.close();
            return empList;
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    public Employee fetchById(int id) throws DataException {
        try {
            con = DBConnection.getSqlConnection();
            Employee employee = null;
            String qry = "SELECT * FROM employee WHERE id=?";
            PreparedStatement preStmt = con.prepareStatement(qry);
            preStmt.setInt(1, id);
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("username"));
                employee.setAddress(resultSet.getString("address"));
                employee.setEmail(resultSet.getString("email"));
                employee.setContact(resultSet.getString("contact"));
            }
            con.close();
            return employee;
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    public void update(Employee employee) throws DataException {
        try {
            con = DBConnection.getSqlConnection();
            String qry = "UPDATE employee SET username=?, address=?, email=?, contact=? WHERE id=?";
            PreparedStatement preStmt = con.prepareStatement(qry);
            preStmt.setString(1, employee.getName());
            preStmt.setString(2, employee.getAddress());
            preStmt.setString(3, employee.getEmail());
            preStmt.setString(4, employee.getContact());
            preStmt.setInt(5, employee.getId());
            preStmt.execute();
            con.close();
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    public void delete(int empId) throws DataException {
        try {
            con = DBConnection.getSqlConnection();
            String qry = "DELETE FROM employee where id=?";
            PreparedStatement preStmt = con.prepareStatement(qry);
            preStmt.setInt(1, empId);
            preStmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    public int getTotalNoOfRecords() throws DataException {
        try {
            con = DBConnection.getSqlConnection();
            String qry = "SELECT COUNT(*) AS total FROM employee";
            PreparedStatement preStmt = con.prepareStatement(qry);
            ResultSet resultSet = preStmt.executeQuery();
            int total = 0;
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
            con.close();
            return total;
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }
}
