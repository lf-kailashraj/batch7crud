package com.lftechnology.batch7crud.constant;

import java.io.File;

public class CommonConstant {
  public static final String MESSAGE = "message";
  public static final String CREATE = "create";
  public static final String EDIT = "edit";
  public static final String DELETE = "delete";
  public static final String SHOW = "show";
  public static final String LIST = "list";

  public static final String SEPARATOR = File.separator;
  public static final String VIEW = SEPARATOR + "WEB-INF" + SEPARATOR + "views" + SEPARATOR;
  public static final String INDEX_PAGE = VIEW + "index.jsp";
  public static final String ERROR_PAGE = VIEW + "error.jsp";
  public static final String LOGIN_PAGE = VIEW + "login.jsp";

  public static final String PAGE_NUMBER = "page";
  public static final String NUMBER_OF_PAGES = "numberOfPages";
  public static final int RECORDS_PER_PAGE = 3;

  public static final String PAGE_NOT_FOUND = "Page Not Found";

  private CommonConstant() {
  }
}
