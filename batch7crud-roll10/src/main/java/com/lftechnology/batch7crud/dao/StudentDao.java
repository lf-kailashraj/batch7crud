package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author binodnme
 * Created on 1/14/16
 */
public class StudentDao {
    private static final Logger LOGGER = Logger.getLogger(StudentDao.class.getName());

    public List<Student> fetch(Integer offset, Integer limit) throws DataException {
        List<Student> studentList = new ArrayList<Student>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet studentResult = null;
        try {
            conn = DbUtils.getConnection();

            String query = "SELECT * FROM student LIMIT ? OFFSET ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            studentResult = ps.executeQuery();

            while (studentResult.next()) {
                Integer id = studentResult.getInt("id");
                Integer rollNo = studentResult.getInt("roll");
                String department = studentResult.getString("department");
                String batch = studentResult.getString("batch");
                String name = studentResult.getString("name");
                String address = studentResult.getString("address");
                Date dob = studentResult.getDate("dob");

                Student student = new Student();
                student.setId(id);
                student.setRoll(rollNo);
                student.setDepartment(department);
                student.setBatch(batch);
                student.setName(name);
                student.setAddress(address);
                student.setDob(dob);

                studentList.add(student);
            }

            return studentList;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            DbUtils.closeResultSet(studentResult);
            DbUtils.closePreparedStatement(ps);
            DbUtils.closeConnection(conn);
        }
    }

    public void insert(Student stud) throws DataException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DbUtils.getConnection();

            String studentQuery = "INSERT INTO student (name,address,dob,department,batch,roll) VALUES(?,?,?,?,?,?)";
            ps = conn.prepareStatement(studentQuery);
            ps.setString(1, stud.getName());
            ps.setString(2, stud.getAddress());
            ps.setDate(3, new java.sql.Date(stud.getDob().getTime()));
            ps.setString(4, stud.getDepartment());
            ps.setString(5, stud.getBatch());
            ps.setInt(6, stud.getRoll());
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            DbUtils.closePreparedStatement(ps);
            DbUtils.closeConnection(conn);
        }

    }

    public void delete(Integer studentId) throws DataException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DbUtils.getConnection();

            String query = "DELETE FROM student WHERE id=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentId);
            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            DbUtils.closePreparedStatement(ps);
            DbUtils.closeConnection(conn);
        }
    }

    public void update(Student student) throws DataException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DbUtils.getConnection();

            String studentQuery = "UPDATE student SET name=?, address=?, dob=?, department=?, batch=?, roll=? WHERE id=?";
            ps = conn.prepareStatement(studentQuery);
            ps.setString(1, student.getName());
            ps.setString(2, student.getAddress());
            ps.setDate(3, new java.sql.Date(student.getDob().getTime()));
            ps.setString(4, student.getDepartment());
            ps.setString(5, student.getBatch());
            ps.setInt(6, student.getRoll());
            ps.setInt(7, student.getId());
            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            DbUtils.closePreparedStatement(ps);
            DbUtils.closeConnection(conn);
        }
    }

    public Student fetchById(Integer id) throws DataException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet studentResult = null;
        try {
            String query = "SELECT * FROM student WHERE id=?";
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            studentResult = ps.executeQuery();

            if (studentResult.next()) {
                Student student = new Student();
                student.setId(studentResult.getInt("id"));
                student.setRoll(studentResult.getInt("roll"));
                student.setDepartment(studentResult.getString("department"));
                student.setBatch(studentResult.getString("batch"));

                student.setName(studentResult.getString("name"));
                student.setAddress(studentResult.getString("address"));
                student.setDob(studentResult.getDate("dob"));

                ps.close();
                conn.close();
                return student;
            } else {
                ps.close();
                conn.close();
                return null;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException(e.getMessage());
        } finally {
            DbUtils.closeResultSet(studentResult);
            DbUtils.closePreparedStatement(ps);
            DbUtils.closeConnection(conn);
        }

    }

    public Integer fetchTotalRecordNumber() throws DataException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT count(*) AS total FROM student";
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                Integer totalRecordNum = rs.getInt("total");
                ps.close();
                conn.close();
                return totalRecordNum;
            } else {
                ps.close();
                conn.close();
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DataException();
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closePreparedStatement(ps);
            DbUtils.closeConnection(conn);
        }
    }
}
