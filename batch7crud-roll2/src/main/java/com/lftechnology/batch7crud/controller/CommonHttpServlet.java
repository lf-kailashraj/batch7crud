package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {

  protected String[] getPathParameters(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split("/");
  }

  protected int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = getPathParameters(request);
    return Integer.parseInt(paths[index]);
  }

  protected int getPageNo(HttpServletRequest request) {
    try {
      if (request.getParameter(AttributeConstants.PAGE) != null) {
        return Integer.parseInt(request.getParameter(AttributeConstants.PAGE));
      } else {
        return 1;
      }
    } catch (NumberFormatException e) {
      return 1;
    }
  }

  protected String getPageFromPath(HttpServletRequest request) {
    String[] pathParts = getPathParameters(request);
    String page = "No Page";

    if (pathParts.length == 2) {
      page = AppConstants.EMPLOYEE;
    } else if (pathParts.length == 3) {
      if (AppConstants.CREATE.equals(pathParts[2])) {
        page = AppConstants.CREATE;
      } else if (AppConstants.EMPLOYEE.equals(pathParts[1])) {
        page = AppConstants.VIEW;
      } else if (AppConstants.LOGIN.equals(pathParts[2])) {
        page = AppConstants.LOGIN;
      } else if (AppConstants.LOGOUT.equals(pathParts[2])) {
        page = AppConstants.LOGOUT;
      }
    } else if (pathParts.length == 4) {
      if (AppConstants.EDIT.equals(pathParts[3])) {
        page = AppConstants.EDIT;
      } else if (AppConstants.DELETE.equals(pathParts[3])) {
        page = AppConstants.DELETE;
      }
    }
    return page;
  }
}
