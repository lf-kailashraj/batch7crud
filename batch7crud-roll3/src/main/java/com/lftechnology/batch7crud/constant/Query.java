package com.lftechnology.batch7crud.constant;

public class Query {
  public static final String STUDENT_LIST = "select * from Students LIMIT ? OFFSET ?";
  public static final String STUDENT_FETCH_TOTAL = "SELECT COUNT(*) AS total FROM Students";
  public static final String STUDENT_FETCH_BY_ID = "Select * from Students where id=?";
  public static final String STUDENT_EDIT = "Update Students set roll=?, name=? where id=?";
  public static final String STUDENT_DELETE = "Delete from Students where id=?";
  public static final String STUDENT_INSERT = "insert into Students(roll, name) values (?,?)";

  private Query() {
  }
}
