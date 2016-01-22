package com.lftechnology.batch7crud.constant;

import java.io.File;

/**
 * Created by Pratish Shrestha <pratishshrestha@lftechnology.com> on 1/21/16.
 */
public class UrlConstants {
  public static final String SEPARATOR = File.separator;

  public static final String VIEW = SEPARATOR + "WEB-INF" + SEPARATOR + "views" + SEPARATOR;
  public static final String EMPLOYEE_VIEW = VIEW + "employee" + SEPARATOR;

  public static final String URL_INDEX_PAGE = "/WEB-INF/views/index.jsp";

  public static final String URL_EMPLOYEE_LISTING_PAGE = EMPLOYEE_VIEW + "employees.jsp";
  public static final String URL_EMPLOYEE_CREATE_PAGE = EMPLOYEE_VIEW + "create.jsp";
  public static final String URL_EMPLOYEE_PROFILE_PAGE = EMPLOYEE_VIEW + "profile.jsp";
  public static final String URL_EMPLOYEE_EDIT_PAGE = EMPLOYEE_VIEW + "edit.jsp";

  public static final String URL_ERROR_PAGE = VIEW + "error.jsp";

  private UrlConstants() {

  }
}
