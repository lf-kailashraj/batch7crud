package com.lftechnology.batch7crud.constants;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/21/16.
 */
public class QueryConstants {
  public static final String EMPLOYEE_INSERT = "INSERT INTO employee (username,address,email,contact) VALUES (?,?,?,?)";
  public static final String EMPLOYEE_SELECT_LIMIT_OFFSET = "SELECT * FROM employee ORDER BY id LIMIT ? OFFSET ?";
  public static final String EMPLOYEE_SELECT_BY_ID = "SELECT * FROM employee WHERE id=?";
  public static final String EMPLOYEE_UPDATE_BY_ID = "UPDATE employee SET username=?, address=?, email=?, contact=? WHERE id=?";
  public static final String EMPLOYEE_DELETE_BY_ID = "DELETE FROM employee where id=?";
  public static final String EMPLOYEE_SELECT_COUNT = "SELECT COUNT(*) AS total FROM employee";

  private QueryConstants() {

  }
}
