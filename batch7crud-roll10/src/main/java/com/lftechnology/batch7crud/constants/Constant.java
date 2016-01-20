package com.lftechnology.batch7crud.constants;

import java.io.File;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/20/16
 */
public class Constant {
  public static final String ERROR_MESSAGE = "errorMessage";
  public static final String WEB_INF = "WEB-INF";
  public static final String VIEWS = "views";
  public static final String STUDENT_LIST_VIEW = File.separator + WEB_INF + File.separator + VIEWS + File.separator + "students.jsp";
  public static final String STUDENT_CREATE_VIEW = File.separator + WEB_INF + File.separator + VIEWS + File.separator + "createStudent.jsp";
  public static final String STUDENT_EDIT_VIEW = File.separator + WEB_INF + File.separator + VIEWS + File.separator + "editStudent.jsp";
  public static final String ERROR_PAGE = File.separator+WEB_INF+File.separator+VIEWS+File.separator+"error.jsp";
  public static final String TOTAL_PAGES = "totalPages";
  public static final String CURRENT_PAGE = "currentPage";
  public static final String STUDENT_LIST_URL = File.pathSeparator + "students";

  public static final Integer TOTAL_DATA_TO_FETCH = 2;

  public static final String CREATE = "create";
  public static final String EDIT = "edit";
  public static final String DELETE = "delete";

  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String ADDRESS = "address";
  public static final String DOB = "dob";
  public static final String DEPARTMENT = "department";
  public static final String BATCH = "batch";
  public static final String ROLL = "roll";

  private Constant(){

  }
}
