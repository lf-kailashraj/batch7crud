package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {

  protected void displayPageNotFound(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    req.setAttribute(AttributeConstants.ERROR_MESSAGE, AppConstants.PAGE_NOT_FOUND_MESSAGE);
    req.getRequestDispatcher(UrlConstants.PAGE_NOT_FOUND).forward(req, res);
  }

  protected void displayErrorPage(HttpServletRequest req, HttpServletResponse res, String message) throws ServletException, IOException {
    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    req.setAttribute(AttributeConstants.ERROR_MESSAGE, message);
    req.getRequestDispatcher(UrlConstants.ERROR_PAGE).forward(req, res);
  }

  protected String[] getPathParameters(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split("/");
  }

  protected int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = getPathParameters(request);
    return Integer.parseInt(paths[index]);
  }

  protected String getPageFromPath(HttpServletRequest request) {
    String[] pathParts = getPathParameters(request);
    String page;

    if (pathParts.length == 2 && AppConstants.EMPLOYEE.equals(pathParts[1])) {
      page = AppConstants.EMPLOYEE;
    } else if (pathParts.length == 3 && AppConstants.CREATE.equals(pathParts[2])) {
      page = AppConstants.CREATE;
    } else if (pathParts.length == 4 && AppConstants.EDIT.equals(pathParts[3])) {
      page = AppConstants.EDIT;
    } else if (pathParts.length == 4 && AppConstants.DELETE.equals(pathParts[3])) {
      page = AppConstants.DELETE;
    } else if (pathParts.length == 3 && AppConstants.EMPLOYEE.equals(pathParts[1])) {
      page = AppConstants.VIEW;
    } else if (pathParts.length == 3 && AppConstants.LOGIN.equals(pathParts[2])) {
      page = AppConstants.LOGIN;
    } else if (pathParts.length == 3 && AppConstants.LOGOUT.equals(pathParts[2])) {
      page = AppConstants.LOGOUT;
    } else {
      page = "Not a page";
    }
    return page;
  }
}
