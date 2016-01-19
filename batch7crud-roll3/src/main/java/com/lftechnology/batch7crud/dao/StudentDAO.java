package com.lftechnology.batch7crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lftechnology.batch7crud.db.DBConnection;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;

public class StudentDAO {
	Student student = new Student();

	public void insert(Student student) throws DataException {
		try {
			String sql = "insert into Students(roll, name) values (?,?)";

			Connection conn = DBConnection.getConnection();
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, student.getRoll());
			stmnt.setString(2, student.getName());
			stmnt.execute();
		} catch (SQLException e) {
			throw new DataException(e.getMessage());
		}
	}

	public List<Student> fetch(int page, int pageSize) throws DataException {
		try {
			String sql = "select * from Students limit ? offset ?";

			List<Student> stdList = new ArrayList<Student>();

			Connection conn = DBConnection.getConnection();
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, pageSize);
			stmnt.setInt(2, (page - 1) * pageSize);
			ResultSet result = stmnt.executeQuery();

			while (result.next()) {
				student = new Student();
				student.setId(result.getInt("id"));
				student.setRoll(result.getInt("roll"));
				student.setName(result.getString("name"));
				stdList.add(student);
			}

			return stdList;
		} catch (SQLException e) {
			throw new DataException(e.getMessage());
		}
	}

	public int fetchTotal() throws DataException {
		try {
			String sql = "SELECT COUNT(*) AS total FROM Students";
			int totalSize = 0;
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmnt = conn.prepareStatement(sql);
			ResultSet count = stmnt.executeQuery();

			while (count.next()) {
				totalSize = count.getInt("total");
			}
			return totalSize;
		} catch (SQLException e) {
			throw new DataException(e.getMessage());
		}
	}

	public Student fetchStudentById(int id) throws DataException {
		try {
			String sql = "Select * from Students where id=?";

			Connection conn = DBConnection.getConnection();
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, id);
			ResultSet result = stmnt.executeQuery();

			while (result.next()) {
				student.setId(result.getInt("id"));
				student.setRoll(result.getInt("roll"));
				student.setName(result.getString("name"));
			}
			return student;
		} catch (SQLException e) {
			throw new DataException(e.getMessage());
		}
	}

	public void edit(Student student, int id) throws DataException {
		try {
			String sql = "Update Students set roll=?, name=? where id=?";

			Connection conn = DBConnection.getConnection();
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, student.getRoll());
			stmnt.setString(2, student.getName());
			stmnt.setInt(3, id);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			throw new DataException(e.getMessage());
		}

	}

	public void delete(int id) throws DataException {
		try {
			String sql = "Delete from Students where id=?";

			Connection conn = DBConnection.getConnection();
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, id);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			throw new DataException(e.getMessage());
		}
	}

}
