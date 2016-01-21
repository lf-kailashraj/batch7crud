package com.lftechnology.batch7crud.constants;

/**
 * Created by sanjay on 1/21/16.
 */
public class Query {
  public static final String UPDATE = "UPDATE tbl_userinfo SET firstname=?, middlename=?, lastname=?, address=?, grade=? WHERE id=?";
  public static final String LIST = "SELECT * FROM tbl_userinfo LIMIT ? OFFSET ?";
  public static final String INSERT = "INSERT INTO tbl_userinfo (firstname,middlename,lastname,address,grade) VALUES (?,?,?,?,?)";
  public static final String SELECT_BY_ID = "SELECT * FROM tbl_userinfo WHERE id=?";
  public static final String DELETE = "DELETE FROM tbl_userinfo WHERE id=?";
  public static final String COUNT_STUDENTS = "SELECT count(*) FROM tbl_userinfo";

  private Query(){}

}
