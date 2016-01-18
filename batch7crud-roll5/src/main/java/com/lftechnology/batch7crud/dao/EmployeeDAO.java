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

	public boolean Create(Employee employee) {
		try {
			String sql = "insert into employee (first_name, last_name, department, address)" + "values(?, ?, ?, ?)";
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getDepartment());
			ps.setString(3, employee.getAddress());

			if (ps.executeUpdate() != 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			throw new DataException();
		}

	}
}
