package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.constant.EntityConstant;
import com.lftechnology.batch7crud.constant.QueryConstant;
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
 * A StudentDao class performs the CRUD operation in Student entity.
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/14/16
 */
public class StudentDao {
  private static final Logger LOGGER = Logger.getLogger(StudentDao.class.getName());

  public List<Student> fetch(Integer offset, Integer limit) throws DataException {
    List<Student> studentList = new ArrayList<>();
    ResultSet studentResult;

    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryConstant.FETCH_STUDENT_LIMIT_OFFSET)
    ) {

      ps.setInt(1, limit);
      ps.setInt(2, offset);
      studentResult = ps.executeQuery();

      while (studentResult.next()) {
        Integer id = studentResult.getInt(EntityConstant.ID);
        Integer rollNo = studentResult.getInt(EntityConstant.ROLL);
        String department = studentResult.getString(EntityConstant.DEPARTMENT);
        String batch = studentResult.getString(EntityConstant.BATCH);
        String name = studentResult.getString(EntityConstant.NAME);
        String address = studentResult.getString(EntityConstant.ADDRESS);
        Date dob = studentResult.getDate(EntityConstant.DOB);

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
    }
  }

  public void insert(Student stud) throws DataException {
    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryConstant.INSERT_INTO_STUDENT)
    ) {

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
    }
  }

  public void delete(Integer studentId) throws DataException {
    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryConstant.DELETE_FROM_STUDENT)
    ) {

      ps.setInt(1, studentId);
      ps.executeUpdate();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void update(Student student) throws DataException {
    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryConstant.UPDATE_STUDENT)
    ) {
      ps.setString(1, student.getName());
      ps.setString(2, student.getAddress());
      ps.setDate(3, new java.sql.Date(student.getDob().getTime()));
      ps.setString(4, student.getDepartment());
      ps.setString(5, student.getBatch());
      ps.setInt(6, student.getRoll());
      ps.setInt(7, student.getId());
      ps.executeUpdate();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Student fetchById(Integer id) throws DataException {
    ResultSet studentResult;
    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryConstant.FETCH_STUDENT_BY_ID)
    ) {
      ps.setInt(1, id);
      studentResult = ps.executeQuery();

      Student student = null;
      if (studentResult.next()) {
        student = new Student();
        student.setId(studentResult.getInt(EntityConstant.ID));
        student.setRoll(studentResult.getInt(EntityConstant.ROLL));
        student.setDepartment(studentResult.getString(EntityConstant.DEPARTMENT));
        student.setBatch(studentResult.getString(EntityConstant.BATCH));

        student.setName(studentResult.getString(EntityConstant.NAME));
        student.setAddress(studentResult.getString(EntityConstant.ADDRESS));
        student.setDob(studentResult.getDate(EntityConstant.DOB));

      }
      return student;

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Integer fetchTotalRecordNumber() throws DataException {
    ResultSet rs;
    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryConstant.FETCH_TOTAL_STUDENT_COUNT)
    ) {
      rs = ps.executeQuery();

      if (rs.next()) {
        return rs.getInt("total");
      } else {
        return 0;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }
}
