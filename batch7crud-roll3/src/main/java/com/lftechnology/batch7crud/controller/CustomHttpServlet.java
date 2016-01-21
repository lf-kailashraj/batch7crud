package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lftechnology.batch7crud.constant.CommonConstant.*;

/**
 * This is an abstract class which is extended by servlets. It contains commonly
 * used methods by other servlets.
 * 
 * @author bishal
 *
 */
public abstract class CustomHttpServlet extends HttpServlet {

  private static final Logger LOGGER = Logger.getLogger(CustomHttpServlet.class.getName());

  public void show404(HttpServletRequest request, HttpServletResponse response) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    request.setAttribute(MESSAGE, PAGE_NOT_FOUND);

    try {
      request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
    } catch (ServletException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public void show500(HttpServletRequest request, HttpServletResponse response, Throwable e) {
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    request.setAttribute(MESSAGE, e.getMessage());
    RequestDispatcher view = request.getRequestDispatcher(ERROR_PAGE);
    try {
      view.forward(request, response);
    } catch (ServletException | IOException e1) {
      LOGGER.log(Level.SEVERE, e1.getMessage(), e1);
    }
  }

  public String[] parameterValues(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split("/");
  }

  public int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = parameterValues(request);
    return Integer.parseInt(paths[index]);
  }

  public int getPageNumber(HttpServletRequest request) {
    if (request.getParameter(PAGE_NUMBER) != null) {
      return Integer.parseInt(request.getParameter(PAGE_NUMBER));
    } else {
      return 1;
    }
  }

}
