package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.util.DBConnection;

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
    public void insertAllData(Employee employee) throws SQLException{
        Connection con = DBConnection.getSqlConnection();

        String qry = "INSERT INTO userinfo (username,address,email,contact) VALUES (?,?,?,?)";
        PreparedStatement preStmt = con.prepareStatement(qry);
        preStmt.setString(1, employee.getName());
        preStmt.setString(2, employee.getAddress());
        preStmt.setString(3, employee.getEmail());
        preStmt.setString(4, employee.getContact());
        preStmt.execute();
    }

    public List<Employee> getPageData(int page) throws SQLException{
        Connection con = DBConnection.getSqlConnection();
        List<Employee> empList = new ArrayList<Employee>();

        String qry = "SELECT * FROM userinfo OFFSET 0";
        PreparedStatement preStmt = con.prepareStatement(qry);
        ResultSet resultSet = preStmt.executeQuery();

        while (resultSet.next()){
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("username"));
            employee.setAddress(resultSet.getString("address"));
            employee.setEmail(resultSet.getString("email"));
            employee.setContact(resultSet.getString("contact"));

            empList.add(employee);
        }
        return empList;
    }
    public void delete(int empId) throws SQLException{
        Connection con = DBConnection.getSqlConnection();

        String qry ="DELETE FROM userinfo where id=?";
        PreparedStatement preStmt = con.prepareStatement(qry);
        preStmt.setInt(1,empId);
        preStmt.executeUpdate();
    }
}
