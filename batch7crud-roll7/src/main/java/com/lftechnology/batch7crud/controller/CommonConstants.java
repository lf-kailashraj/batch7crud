package com.lftechnology.batch7crud.controller;

import java.io.File;

/**
 * Created by Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com> on 1/21/16.
 */
public class CommonConstants {
  public static final String LIST_URL = "students";
  public static final String NEW_ENTRY = "newEntry";
  public static final String EDIT = "edit";
  public static final String DELETE = "delete";
  private static final String VIEW_BASE = File.separator + "WEB-INF" + File.separator + "views" + File.separator;
  public static final String STUDENTS_LIST_VIEW = VIEW_BASE + "students.jsp";
  public static final String NEW_ENTRY_VIEW = VIEW_BASE + "newEntry.jsp";
  public static final String EDIT_ENTRY_VIEW = VIEW_BASE + "editEntry.jsp";
  public static final String SHOW_STUDENT_VIEW = VIEW_BASE + "show.jsp";
  public static final String ERROR_VIEW = VIEW_BASE + "error.jsp";
  public static final String INDEX_VIEW = VIEW_BASE + "index.jsp";
  public static final int PAGE_SIZE = 3;

  private CommonConstants(){
    
  }
}
