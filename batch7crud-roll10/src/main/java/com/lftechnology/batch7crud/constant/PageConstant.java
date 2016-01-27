package com.lftechnology.batch7crud.constant;

import java.io.File;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/21/16
 */
public class PageConstant {
  public static final String ERROR_MESSAGE = "errorMessage";
  public static final String WEB_INF = "WEB-INF";
  public static final String VIEWS = "views";
  public static final String LOGIN_VIEW = File.separator + WEB_INF + File.separator + VIEWS + File.separator + "login.jsp";
  public static final String STUDENT_LIST_VIEW = File.separator + WEB_INF + File.separator + VIEWS + File.separator + "students.jsp";
  public static final String STUDENT_CREATE_VIEW = File.separator + WEB_INF + File.separator + VIEWS + File.separator + "createStudent.jsp";
  public static final String STUDENT_EDIT_VIEW = File.separator + WEB_INF + File.separator + VIEWS + File.separator + "editStudent.jsp";
  public static final String ERROR_PAGE = File.separator + WEB_INF + File.separator + VIEWS + File.separator + "error.jsp";
  public static final String TOTAL_PAGES = "totalPages";
  public static final String CURRENT_PAGE = "currentPage";
  public static final String STUDENT_LIST_URL = "/students";
  public static final String LOGIN_URL = "/auth/login";
  public static final String CREATE = "create";
  public static final String EDIT = "edit";
  public static final String DELETE = "delete";
  public static final String LOGIN = "login";
  public static final String LOGOUT = "logout";
  public static final Integer TOTAL_DATA_TO_FETCH = 2;

  private PageConstant() {
  }
}
