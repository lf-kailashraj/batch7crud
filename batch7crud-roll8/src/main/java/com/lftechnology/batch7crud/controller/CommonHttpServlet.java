package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/21/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(CommonHttpServlet.class.getName());

  protected void pageNotFound(HttpServletRequest request, HttpServletResponse response) {
    try {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      request.setAttribute(AttributeConstants.ERROR_MESSAGE, AppConstants.PAGE_NOT_FOUND_MESSAGE);
      request.getRequestDispatcher(UrlConstants.PAGE_NOT_FOUND).forward(request, response);
    } catch (ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  protected void errorPage(HttpServletRequest request, HttpServletResponse response, String message) {
    try {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      request.setAttribute(AttributeConstants.ERROR_MESSAGE, message);
      request.getRequestDispatcher(UrlConstants.ERROR_PAGE).forward(request, response);
    } catch (ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  protected String[] getPathParams(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split("/");
  }

  protected int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = getPathParams(request);
    return Integer.parseInt(paths[index]);
  }
}
