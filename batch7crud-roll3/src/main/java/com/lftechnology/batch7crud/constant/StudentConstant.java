package com.lftechnology.batch7crud.constant;

import java.io.File;

public class StudentConstant {
  public static final String STUDENT = "student";
  public static final String STUDENT_LIST = "studentList";
  public static final String STUDENT_LIST_CONTROLLER = "/students";

  public static final String ROLL = "roll";
  public static final String NAME = "name";

  public static final String SEPARATOR = File.separator;
  public static final String VIEW = SEPARATOR + "WEB-INF" + SEPARATOR + "views" + SEPARATOR + "students" + SEPARATOR;
  public static final String EDIT_PAGE = VIEW + "edit.jsp";
  public static final String CREATE_PAGE = VIEW + "create.jsp";
  public static final String SHOW_PAGE = VIEW + "show.jsp";
  public static final String LIST_PAGE = VIEW + "list.jsp";

  public static final String INVALID_ROLL_MESSAGE = "Invalid Roll Number";
  
 

  private StudentConstant() {
  }
}
