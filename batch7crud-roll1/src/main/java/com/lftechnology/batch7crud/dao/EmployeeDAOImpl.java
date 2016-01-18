package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.database.DatabaseConnection;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl{

	DatabaseConnection databaseConnection = new DatabaseConnection();

	public int insert(Employee employee) throws DataException {

        try {
            Connection connection = databaseConnection.getConnection();
            String sqlQuery = "insert into employee(id,username,password,fullname,department,address) values(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getUserName());
            statement.setString(3, employee.getPassword());
            statement.setString(4, employee.getFullName());
            statement.setString(5, employee.getDepartment());
            statement.setString(6, employee.getAddress());
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new DataException();
        }
	}
	
	public List<Employee> fetch(int page) throws DataException{

        try{
            Connection connection = databaseConnection.getConnection();
            String sqlQuery = "select * from employee order by id limit 2";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet result = statement.executeQuery();
            List<Employee> employees = new ArrayList<Employee>();
            Employee employee = null;

            while(result.next()){
                employee = new Employee();
                employee.setId(result.getInt("id"));
                employee.setUserName(result.getString("userName"));
                employee.setPassword(result.getString("password"));
                employee.setFullName(result.getString("fullName"));
                employee.setAddress(result.getString("address"));
                employee.setDepartment(result.getString("department"));
                employees.add(employee);
            }
            return employees;
        }catch (SQLException e){
            throw new DataException();
        }

	}

    public Employee getEmployeeById(int id) throws DataException{

        try{
            Connection connection = databaseConnection.getConnection();
            String sqlQuery = "select * from employee where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            Employee employee = new Employee();
            while(result.next()){
                employee.setId(result.getInt("id"));
                employee.setUserName(result.getString("userName"));
                employee.setPassword(result.getString("password"));
                employee.setFullName(result.getString("fullName"));
                employee.setAddress(result.getString("address"));
                employee.setDepartment(result.getString("department"));
            }
            return employee;
        }catch (SQLException e){
            throw new DataException();
        }
    }

    public int update(Employee employee) throws DataException{
        try{
            Connection connection = databaseConnection.getConnection();
            String sqlQuery = "update employee set id=?, userName=?, password=?,fullName=?," +
                    "department=?,address=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2,employee.getUserName());
            preparedStatement.setString(3,employee.getPassword());
            preparedStatement.setString(4,employee.getFullName());
            preparedStatement.setString(5,employee.getDepartment());
            preparedStatement.setString(6, employee.getAddress());
            preparedStatement.setInt(7, employee.getId());
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DataException();
        }
    }

    public int delete(int id) throws DataException{
        try{
            Connection connection = databaseConnection.getConnection();
            String sqlQuery = "delete from employee where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DataException();
        }
    }
}
