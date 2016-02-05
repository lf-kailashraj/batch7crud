package com.lftechnology.batch7crud.constant;

import java.io.File;

/**
 * Created by achyut on 1/27/16.
 */
public class UrlConstants {
  public static final String WEB_INF = File.separator + "WEB-INF";
  public static final String INDEX_PAGE = "/WEB-INF/views/employees/index.jsp";
  public static final String LIST_EMPLOYEE = "/WEB-INF/views/employees/employeeList.jsp";
  public static final String ADD_EMPLOYEE = "/WEB-INF/views/employees/addEmployee.jsp";
  public static final String EDIT_EMPLOYEE = "/WEB-INF/views/employees/editEmployee.jsp";
  public static final String LOGIN_PAGE = "/WEB-INF/views/loginPage.jsp";
  public static final String ADD_USER = "/WEB-INF/views/users/addUser.jsp";



  private UrlConstants() {
  }
}

