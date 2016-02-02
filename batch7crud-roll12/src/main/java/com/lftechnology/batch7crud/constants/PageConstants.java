package com.lftechnology.batch7crud.constants;

import java.io.File;

/**
 * Created by sagarmatha on 1/27/16.
 */
public class PageConstants {
    private static final String SEPARATOR = File.separator;
    private static final String BASE = SEPARATOR+"WEB-INF" + SEPARATOR + "views" +SEPARATOR;
    public static final String NEW_STUDENT = BASE + "student.jsp";
    public static final String LIST_VIEW = BASE + "listStudent.jsp";
    public static final String UPDATE = BASE + "update.jsp";
    public static final String DETAIL_VIEW = BASE + "detailStudent.jsp";
    public static final String ERROR_PAGE = BASE + "error.jsp";
    public static final String LOGIN_PAGE = BASE + "login.jsp";
    public static final String INDEX_PAGE = BASE + "index.jsp";

    private PageConstants() {
    }
}
