package com.lftechnology.batch7crud.constant;

/**
 * Created by pratishshr on 1/21/16.
 */
public final class QueryConstants {
  public static final String QUERY_SELECT_FROM_EMPLOYEES_LIMIT_OFFSET = "SELECT * FROM employees LIMIT ? OFFSET ?";
  public static final String QUERY_SELECT_FROM_EMPLOYEES_WHERE_ID = "SELECT * FROM employees WHERE id=?";
  public static final String QUERY_INSERT_INTO_EMPLOYEES = "INSERT INTO employees(first_name, last_name, station) VALUES(?,?,?)";
  public static final String QUERY_UPDATE_SET_EMPLOYEES = "UPDATE employees SET first_name=?, last_name=?, station=? WHERE id=?";
  public static final String QUERY_DELETE_FROM_EMPLOYEES_WHERE_ID = "DELETE FROM employees WHERE id=?";
  public static final String QUERY_COUNT_RECORDS_FROM_EMPLOYEES = "SELECT COUNT(*) FROM employees";

  private QueryConstants() {

  }
}
