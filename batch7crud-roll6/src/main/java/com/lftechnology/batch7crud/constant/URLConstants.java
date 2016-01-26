package com.lftechnology.batch7crud.constant;

import java.io.File;

/**
 * 
 * @author madandhungana <madandhungana@lftechnology.com> Jan 21, 2016 batch7crud-roll6
 * 
 */
public class URLConstants {

  public static final String WEB_INF = File.separator + "WEB-INF";
  public static final String VIEWS = File.separator + "views" + File.separator;
  public static final String INDEX_PAGE = WEB_INF + VIEWS + "index.jsp";
  public static final String LIST_USER = WEB_INF + VIEWS + "listUser.jsp";
  public static final String ADD_USER = WEB_INF + VIEWS + "addUser.jsp";
  public static final String EDIT_USER = WEB_INF + VIEWS + "editUser.jsp";
  public static final String ERROR_PAGE = WEB_INF + VIEWS + "error.jsp";
  public static final String USER_LIST_SERVLET = "" + File.separator + "batch7crud-roll6" + File.separator + "users";

  private URLConstants() {

  }

}
