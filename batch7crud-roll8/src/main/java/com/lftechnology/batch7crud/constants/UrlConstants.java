package com.lftechnology.batch7crud.constants;

import java.io.File;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/21/16.
 */
public class UrlConstants {
  public static final String FILE_SEPARATOR = File.separator;
  public static final String PATH_SEPARATOR = "/";
  public static final String VIEWS = FILE_SEPARATOR + "WEB-INF" + FILE_SEPARATOR + "views" + FILE_SEPARATOR;

  private static final String INDEX = "index.jsp";

  public static final String EMPLOYEE_ROUTE = FILE_SEPARATOR + "employees";
  public static final String EMPLOYEE_LIST_PAGE = VIEWS + EMPLOYEE_ROUTE + FILE_SEPARATOR + INDEX;
  public static final String EMPLOYEE_CREATE_PAGE = VIEWS + EMPLOYEE_ROUTE + FILE_SEPARATOR + "new.jsp";
  public static final String EMPLOYEE_VIEW_PAGE = VIEWS + EMPLOYEE_ROUTE + FILE_SEPARATOR + "view.jsp";
  public static final String EMPLOYEE_EDIT_PAGE = VIEWS + EMPLOYEE_ROUTE + FILE_SEPARATOR + "edit.jsp";

  public static final String USER_ROUTE = FILE_SEPARATOR + "users";
  public static final String USER_SIGN_IN_ROUTE = USER_ROUTE + FILE_SEPARATOR + "signin";
  public static final String USER_HOME_PAGE = VIEWS + USER_ROUTE + FILE_SEPARATOR + INDEX;
  public static final String USER_CREATE_PAGE = VIEWS + USER_ROUTE + FILE_SEPARATOR + "new.jsp";
  public static final String USER_VIEW_PAGE = VIEWS + USER_ROUTE + FILE_SEPARATOR + "view.jsp";
  public static final String USER_EDIT_PAGE = VIEWS + USER_ROUTE + FILE_SEPARATOR + "edit.jsp";
  public static final String USER_SIGN_UP = VIEWS + USER_ROUTE + FILE_SEPARATOR + "signup.jsp";
  public static final String USER_SIGN_IN = VIEWS + USER_ROUTE + FILE_SEPARATOR + "signin.jsp";

  public static final String INDEX_PAGE = VIEWS + INDEX;

  private UrlConstants() {

  }
}
