package com.lftechnology.batch7crud.constant;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/21/16
 */
public class QueryConstant {
  public static final String FETCH_STUDENT_LIMIT_OFFSET = "SELECT * FROM student LIMIT ? OFFSET ?";
  public static final String FETCH_STUDENT_BY_ID = "SELECT * FROM student WHERE id=?";
  public static final String INSERT_INTO_STUDENT = "INSERT INTO student (name,address,dob,department,batch,roll) VALUES(?,?,?,?,?,?)";
  public static final String DELETE_FROM_STUDENT = "DELETE FROM student WHERE id=?";
  public static final String UPDATE_STUDENT = "UPDATE student SET name=?, address=?, dob=?, department=?, batch=?, roll=? WHERE id=?";
  public static final String FETCH_TOTAL_STUDENT_COUNT = "SELECT count(*) AS total FROM student";

  private QueryConstant() {
  }
}
