package com.lftechnology.batch7crud.constant;

import static com.lftechnology.batch7crud.constant.CommonConstant.*;

public class StudentConstant {
  public static final String STUDENT = "student";
  public static final String STUDENT_LIST = "studentList";
  public static final String STUDENT_LIST_CONTROLLER = "/students";

  public static final String ROLL = "roll";
  public static final String NAME = "name";

  public static final String STUDENTS = VIEW + "students" + SEPARATOR;
  public static final String EDIT_PAGE = STUDENTS + "edit.jsp";
  public static final String CREATE_PAGE = STUDENTS + "create.jsp";
  public static final String SHOW_PAGE = STUDENTS + "show.jsp";
  public static final String LIST_PAGE = STUDENTS + "list.jsp";

  public static final String INVALID_ROLL_MESSAGE = "Invalid Roll Number";
  public static final String ROLL_TOO_LARGE_MESSAGE = "Roll Number too large";
  public static final String INVALID_NAME_MESSAGE = "Invalid Name";

  private StudentConstant() {
  }
}
