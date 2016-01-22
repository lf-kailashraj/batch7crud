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
  public static final String STUDENTS_LIST_VIEW = File.separator + "WEB-INF" + File.separator + "views" + File.separator + "students.jsp";
  public static final String NEW_ENTRY_VIEW = File.separator + "WEB-INF" + File.separator + "views" + File.separator + "newEntry.jsp";
  public static final String EDIT_ENTRY_VIEW = File.separator + "WEB-INF" + File.separator + "views" + File.separator + "editEntry.jsp";
  public static final String SHOW_STUDENT_VIEW = File.separator + "WEB-INF" + File.separator + "views" + File.separator + "show.jsp";
  public static final String ERROR_VIEW = File.separator + "WEB-INF" + File.separator + "views" + File.separator + "error.jsp";
  public static final int PAGE_SIZE = 3;

  private CommonConstants(){
    
  }
}
