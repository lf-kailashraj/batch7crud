package com.lftechnology.batch7crud.constant;

import java.io.File;

/**
 * Created by achyut on 1/27/16.
 */
public class UrlConstants {
  public static final String WEB_INF = File.separator + "WEB-INF";
  public static final String INDEX_PAGE = "/WEB-INF/views/index.jsp";
  public static final String LIST_EMPLOYEE = "/WEB-INF/views/employeeList.jsp";
  public static final String ERROR_PAGE = "/WEB-INF/views/error404.jsp";
  public static final String ADD_EMPLOYEE = "/WEB-INF/views/addEmployee.jsp";
  public static final String EDIT_EMPLOYEE = "/WEB-INF/views/editEmployee.jsp";


  private UrlConstants() {
  }
}

