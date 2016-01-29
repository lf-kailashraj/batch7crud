package com.lftechnology.batch7crud.constant;

import java.io.File;

/**
 * provide access to url constants
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/21/16.
 */
public class URLConstant {

  public static final String VIEWS = File.separator + "WEB-INF" + File.separator + "views" + File.separator;
  public static final String EMPLOYEE_VIEWS = VIEWS + "employee" + File.separator;
  public static final String LIST_PAGE = EMPLOYEE_VIEWS + "list.jsp";
  public static final String CREATE_PAGE = EMPLOYEE_VIEWS + "create.jsp";
  public static final String EDIT_PAGE = EMPLOYEE_VIEWS + "edit.jsp";
  public static final String INDEX_PAGE = VIEWS + "index.jsp";
  public static final String EMPLOYEE_LIST = "/employees";
  public static final String ERROR_PAGE = VIEWS + "error.jsp";
  public static final String LOGIN_PAGE = VIEWS + "login.jsp";

  private URLConstant() {

  }
}
