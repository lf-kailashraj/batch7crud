package com.lftechnology.batch7crud.constant;

import java.io.File;

/**
 * provide access to page constants
 * Created by kiran on 1/21/16.
 */
public class PageConstant {
  public static final String CREATE = "create";
  public static final String DELETE = "delete";
  public static final String EDIT = "edit";
  public static final String VIEWS = File.separator + "WEB-INF"+ File.separator + "views" + File.separator + "employee" + File.separator;
  public static final String LIST_PAGE = VIEWS + "list.jsp";
  public static final String CREATE_PAGE = VIEWS + "create.jsp";
  public static final String EDIT_PAGE = VIEWS + "edit.jsp";
  public static final String EMPLOYEE_LIST = "/employees";
  public static final String PAGE = "page";

  private PageConstant() {

  }
}
