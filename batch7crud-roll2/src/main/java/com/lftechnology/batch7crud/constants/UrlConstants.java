package com.lftechnology.batch7crud.constants;

import java.io.File;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/21/16.
 */
public class UrlConstants {
  public static final String URL_SEPARATOR = File.separator;
  public static final String WEB_INF = "WEB-INF";
  public static final String VIEWS = "views";

  public static final String EMPLOYEE_ROUTE = URL_SEPARATOR + "employees";
  public static final String EMPLOYEE_LIST_PAGE = URL_SEPARATOR + WEB_INF + URL_SEPARATOR + VIEWS + URL_SEPARATOR + "employeesList.jsp";
  public static final String EMPLOYEE_CREATE_PAGE = URL_SEPARATOR + WEB_INF + URL_SEPARATOR + VIEWS + URL_SEPARATOR + "employeeCreate.jsp";
  public static final String EMPLOYEE_VIEW_PAGE = URL_SEPARATOR + WEB_INF + URL_SEPARATOR + VIEWS + URL_SEPARATOR + "employeeView.jsp";
  public static final String EMPLOYEE_EDIT_PAGE = URL_SEPARATOR + WEB_INF + URL_SEPARATOR + VIEWS + URL_SEPARATOR + "employeeEdit.jsp";
  public static final String INDEX_PAGE = URL_SEPARATOR + WEB_INF + URL_SEPARATOR + VIEWS + URL_SEPARATOR + "index.jsp";
  public static final String PAGE_NOT_FOUND = URL_SEPARATOR + WEB_INF + URL_SEPARATOR + VIEWS + URL_SEPARATOR + "pageNotFound.jsp";
  public static final String ERROR_PAGE = URL_SEPARATOR + WEB_INF + URL_SEPARATOR + VIEWS + URL_SEPARATOR + "errorPage.jsp";

  private UrlConstants() {

  }
}
