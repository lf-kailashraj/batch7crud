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

public class EmployeeDAO {

	public void insert(Employee employee)
					throws DataException {

		try {
			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery =
							"insert into employee(id,username,password,fullname,department,address) values(?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, employee.getId());
			statement.setString(2, employee.getUserName());
			statement.setString(3, employee.getPassword());
			statement.setString(4, employee.getFullName());
			statement.setString(5, employee.getDepartment());
			statement.setString(6, employee.getAddress());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DataException();
		}
	}

	public List<Employee> fetch(int page)
					throws DataException {

		try {
			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "select * from employee order by id";
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			ResultSet result = statement.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();

			while (result.next()) {
				Employee employee = new Employee();
				employee.setId(result.getInt("id"));
				employee.setUserName(result.getString("userName"));
				employee.setPassword(result.getString("password"));
				employee.setFullName(result.getString("fullName"));
				employee.setAddress(result.getString("address"));
				employee.setDepartment(result.getString("department"));
				employees.add(employee);
			}
			return employees;
		}
		catch (SQLException e) {
			throw new DataException();
		}

	}

	public Employee fetchById(int id)
					throws DataException {

		try {
			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "select * from employee where id = ?";
			PreparedStatement preparedStatement =
							connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			Employee employee = new Employee();
			while (result.next()) {
				employee.setId(result.getInt("id"));
				employee.setUserName(result.getString("userName"));
				employee.setPassword(result.getString("password"));
				employee.setFullName(result.getString("fullName"));
				employee.setAddress(result.getString("address"));
				employee.setDepartment(result.getString("department"));
			}
			return employee;
		}
		catch (SQLException e) {
			throw new DataException();
		}
	}

	public void update(Employee employee)
					throws DataException {

		try {
			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery =
							"update employee set id=?, userName=?, password=?,fullName=?," +
											"department=?,address=? where id=?";
			PreparedStatement preparedStatement =
							connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getUserName());
			preparedStatement.setString(3, employee.getPassword());
			preparedStatement.setString(4, employee.getFullName());
			preparedStatement.setString(5, employee.getDepartment());
			preparedStatement.setString(6, employee.getAddress());
			preparedStatement.setInt(7, employee.getId());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DataException();
		}
	}

	public void delete(int id)
					throws DataException {

		try {
			Connection connection = DatabaseConnection.getConnection();
			String sqlQuery = "delete from employee where id = ?";
			PreparedStatement preparedStatement =
							connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DataException();
		}
	}

}
