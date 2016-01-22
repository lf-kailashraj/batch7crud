package com.lftechnology.batch7crud.controller;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leapfrog on 1/21/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {
  private static final String INTERNAL_ERROR = "Internal Error";
  private static final String ERROR_MESSAGE = "errorMessage";
  private static final String PAGE_NOT_FOUND = "Page Not Found";
  private static final Logger LOGGER = Logger.getLogger(CommonHttpServlet.class.getName());

  protected void showErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    request.setAttribute(ERROR_MESSAGE, PAGE_NOT_FOUND);
    RequestDispatcher view = request.getRequestDispatcher(CommonConstants.ERROR_VIEW);
    view.forward(request, response);
  }

  protected void showInternalErrorPage(HttpServletRequest request, HttpServletResponse response) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      request.setAttribute(ERROR_MESSAGE, INTERNAL_ERROR);
      RequestDispatcher view = request.getRequestDispatcher(CommonConstants.ERROR_VIEW);
    try{
      view.forward(request, response);
    } catch(ServletException | IOException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public String[] urlParts(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split("/");
  }

  public int urlInteger(HttpServletRequest request, int index) {
    String[] paths = urlParts(request);
    return Integer.parseInt(paths[index]);
  }

  public int getPageNumber(HttpServletRequest request) {
    if (request.getParameter("page") != null) {
      return Integer.parseInt(request.getParameter("page"));
    } else {
      return 1;
    }
  }
}
