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
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;

public class StudentDAO {
	private Logger logger = Logger.getLogger("StudentDAO");

	public void insert(Student student) throws DataException {
		PreparedStatement stmnt = null;
		Connection conn = null;
		try {
			String sql = "insert into Students(roll, name) values (?,?)";
			conn = DBConnection.getConnection();
			stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, student.getRoll());
			stmnt.setString(2, student.getName());
			stmnt.execute();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);

			throw new DataException(e.getMessage());
		} finally {
			closeAll(null, stmnt, conn);
		}
	}

	public List<Student> fetch(int page, int pageSize) throws DataException {
		PreparedStatement stmnt = null;
		Connection conn = null;
		ResultSet result = null;
		try {
			String sql = "select * from Students limit ? offset ?";

			List<Student> stdList = new ArrayList<Student>();

			conn = DBConnection.getConnection();
			stmnt = conn.prepareStatement(sql);

			stmnt.setInt(1, pageSize);
			stmnt.setInt(2, (page - 1) * pageSize);

			result = stmnt.executeQuery();

			while (result.next()) {
				Student student = new Student();
				student.setId(result.getInt("id"));
				student.setRoll(result.getInt("roll"));
				student.setName(result.getString("name"));
				stdList.add(student);
			}

			return stdList;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);

			throw new DataException(e.getMessage());
		} finally {
			closeAll(result, stmnt, conn);
		}
	}

	public int fetchTotal() throws DataException {
		PreparedStatement stmnt = null;
		Connection conn = null;
		ResultSet count = null;
		try {
			String sql = "SELECT COUNT(*) AS total FROM Students";
			int totalSize = 0;
			conn = DBConnection.getConnection();
			stmnt = conn.prepareStatement(sql);

			count = stmnt.executeQuery();

			while (count.next()) {
				totalSize = count.getInt("total");
			}
			return totalSize;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);

			throw new DataException(e.getMessage());
		} finally {
			closeAll(count, stmnt, conn);
		}
	}

	public Student fetchStudentById(int id) throws DataException {
		PreparedStatement stmnt = null;
		Connection conn = null;
		ResultSet result = null;
		try {
			String sql = "Select * from Students where id=?";

			conn = DBConnection.getConnection();
			stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, id);

			result = stmnt.executeQuery();

			Student student = null;
			while (result.next()) {
				student = new Student();
				student.setId(result.getInt("id"));
				student.setRoll(result.getInt("roll"));
				student.setName(result.getString("name"));
			}
			return student;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);

			throw new DataException(e.getMessage());
		} finally {
			closeAll(result, stmnt, conn);
		}
	}

	public void edit(Student student, int id) throws DataException {
		PreparedStatement stmnt = null;
		Connection conn = null;
		try {
			String sql = "Update Students set roll=?, name=? where id=?";

			conn = DBConnection.getConnection();
			stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, student.getRoll());
			stmnt.setString(2, student.getName());
			stmnt.setInt(3, id);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);

			throw new DataException(e.getMessage());
		} finally {
			closeAll(null, stmnt, conn);
		}

	}

	public void delete(int id) throws DataException {
		PreparedStatement stmnt = null;
		Connection conn = null;
		try {
			String sql = "Delete from Students where id=?";

			conn = DBConnection.getConnection();
			stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, id);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);

			throw new DataException(e.getMessage());
		} finally {
			closeAll(null, stmnt, conn);
		}
	}

	private void closeAll(ResultSet result, PreparedStatement stmnt, Connection conn) {
		try {
			if (result != null)
				result.close();
			if (stmnt != null)
				stmnt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e1) {
			logger.log(Level.SEVERE, e1.getMessage(), e1);
		}

	}

}
