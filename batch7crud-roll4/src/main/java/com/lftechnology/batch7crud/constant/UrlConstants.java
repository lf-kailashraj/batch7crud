package com.lftechnology.batch7crud.constant;

import java.io.File;

/**
 * Created by pratishshr on 1/21/16.
 */
public class UrlConstants {
  public static final String SEPARATOR = File.separator;
  public static final String VIEW = SEPARATOR + "WEB-INF" + SEPARATOR + "views" + SEPARATOR;

  public static final String URL_EMPLOYEE_LISTING_PAGE = VIEW + "employees.jsp";
  public static final String URL_EMPLOYEE_CREATE_PAGE = VIEW + "create.jsp";
  public static final String URL_EMPLOYEE_PROFILE_PAGE = VIEW + "profile.jsp";
  public static final String URL_EMPLOYEE_EDIT_PAGE = VIEW + "edit.jsp";

  public static final String URL_ERROR_PAGE = VIEW + "error.jsp";

  private UrlConstants() {

  }
}
