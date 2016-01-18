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
    public void insert(Employee employee) throws DataException {
        try {
            Connection con = DBConnection.getSqlConnection();
            String qry = "INSERT INTO userinfo (username,address,email,contact) VALUES (?,?,?,?)";
            PreparedStatement preStmt = con.prepareStatement(qry);
            preStmt.setString(1, employee.getName());
            preStmt.setString(2, employee.getAddress());
            preStmt.setString(3, employee.getEmail());
            preStmt.setString(4, employee.getContact());
            preStmt.execute();
        } catch (SQLException e) {
            throw new DataException();
        }
    }

    public List<Employee> fetch() throws DataException {
        try {
            Connection con = DBConnection.getSqlConnection();
            List<Employee> empList = new ArrayList<Employee>();
            String qry = "SELECT * FROM userinfo OFFSET 0";
            PreparedStatement preStmt = con.prepareStatement(qry);
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
            return empList;
        } catch (SQLException e) {
            throw new DataException();
        }
    }

    public Employee fetch(int id) throws DataException {
        try {
            Connection con = DBConnection.getSqlConnection();
            Employee employee = null;
            String qry = "SELECT * FROM userinfo WHERE id=?";
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
            return employee;
        } catch (SQLException e) {
            throw new DataException();
        }
    }

    public void update(Employee employee) throws DataException{
        try {
            Connection con = DBConnection.getSqlConnection();
            String qry = "UPDATE userinfo SET username=?, address=?, email=?, contact=? WHERE id=?";
            PreparedStatement preStmt = con.prepareStatement(qry);
            preStmt.setString(1, employee.getName());
            preStmt.setString(2, employee.getAddress());
            preStmt.setString(3, employee.getEmail());
            preStmt.setString(4, employee.getContact());
            preStmt.setInt(5, employee.getId());
            preStmt.execute();
        } catch (SQLException e) {
            throw new DataException();
        }
    }

    public void delete(int empId) throws DataException {
        try {
            Connection con = DBConnection.getSqlConnection();
            String qry = "DELETE FROM userinfo where id=?";
            PreparedStatement preStmt = con.prepareStatement(qry);
            preStmt.setInt(1, empId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataException();
        }
    }
}
