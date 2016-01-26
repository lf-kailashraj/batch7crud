package com.lftechnology.batch7crud.constants;

import java.io.File;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/21/16.
 */
public class UrlConstants {
  public static final String FILE_SEPARATOR = File.separator;
  public static final String VIEWS = FILE_SEPARATOR + "WEB-INF" + FILE_SEPARATOR + "views" + FILE_SEPARATOR;

  public static final String EMPLOYEE_ROUTE = "/employees";
  public static final String LOGIN_ROUTE = "/login";
  public static final String INDEX_ROUTE = "/";
  public static final String EMPLOYEE_LIST_PAGE = VIEWS + "employeesList.jsp";
  public static final String EMPLOYEE_CREATE_PAGE = VIEWS + "employeeCreate.jsp";
  public static final String EMPLOYEE_VIEW_PAGE = VIEWS + "employeeView.jsp";
  public static final String EMPLOYEE_EDIT_PAGE = VIEWS + "employeeEdit.jsp";
  public static final String INDEX_PAGE = VIEWS + "index.jsp";
  public static final String LOGIN_PAGE = VIEWS + "login.jsp";
  public static final String PAGE_NOT_FOUND = VIEWS + "pageNotFound.jsp";
  public static final String ERROR_PAGE = VIEWS + "errorPage.jsp";

  private UrlConstants() {

  }
}
