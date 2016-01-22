package com.lftechnology.batch7crud.constants;

import java.io.File;

/**
 * Created by sanjay on 1/21/16.
 */
public class PageConstant {
  private static final String SEPARATOR = File.separator;
  private static final String BASE = SEPARATOR+"WEB-INF" + SEPARATOR + "views" +SEPARATOR;
  public static final String EDIT = BASE + "edit.jsp";
  public static final String DETAIL_VIEW = BASE + "detail-view.jsp";
  public static final String LIST_VIEW = BASE + "list-view.jsp";
  public static final String NEW_STUDENT = BASE + "newStudent.jsp";
  public static final String INDEX_PAGE = BASE + "index.jsp";
  public static final String ERROR_PAGE = BASE + "error-page.jsp";

  private PageConstant() {
  }
}
