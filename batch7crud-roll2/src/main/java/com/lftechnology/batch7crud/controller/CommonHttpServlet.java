package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    return urlPath.split(File.separator);
  }

  protected int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = getPathParameters(request);
    return Integer.parseInt(paths[index]);
  }
}
