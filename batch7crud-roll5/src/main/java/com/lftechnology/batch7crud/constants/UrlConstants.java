package com.lftechnology.batch7crud.constants;

import java.io.File;

public class UrlConstants {
    public static final String SEPERATOR = File.separator;
    private static final String WEB_INF = "WEB-INF";
    private static final String VIEWS = "views";
    public static final String EMPLOYEE_LIST_URL = SEPERATOR + "batch7crud-roll5" + SEPERATOR + "employees";
    public static final String EMPLOYEE_FETCH_URL = SEPERATOR + WEB_INF + SEPERATOR + VIEWS + SEPERATOR + "employee.jsp";
    public static final String EMPLOYEE_EDIT_URL = SEPERATOR + WEB_INF + SEPERATOR + VIEWS + SEPERATOR + "editEmployee.jsp";
    public static final String EMPLOYEE_CREATE_URL = SEPERATOR + WEB_INF + SEPERATOR + VIEWS + SEPERATOR + "createEmployee.jsp";
    public static final String ERROR_PAGE = SEPERATOR + WEB_INF + SEPERATOR + VIEWS + SEPERATOR + "error.jsp";
    public static final String HOME_PAGE = SEPERATOR + WEB_INF + SEPERATOR + VIEWS + SEPERATOR + "index.jsp";
    public static final String LOGIN_PAGE = SEPERATOR + WEB_INF + SEPERATOR + VIEWS + SEPERATOR + "login.jsp";

    private UrlConstants() {

    }

}
