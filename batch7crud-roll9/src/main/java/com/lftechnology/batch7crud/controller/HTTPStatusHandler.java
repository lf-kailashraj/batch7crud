package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/19/16.
 */
public abstract class HTTPStatusHandler extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(HTTPStatusHandler.class.getName());


  protected void show404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    request.setAttribute(Message.MESSAGE, Message.PAGE_NOT_FOUND);

    RequestDispatcher view = request.getRequestDispatcher(Page.ERROR_PAGE);
    view.forward(request, response);
  }

  protected void show500(HttpServletRequest request, HttpServletResponse response, Throwable e) {
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    request.setAttribute(Message.MESSAGE, e.getMessage());
    try {
      RequestDispatcher view = request.getRequestDispatcher(Page.ERROR_PAGE);
      view.forward(request, response);
    }
    catch(IOException | ServletException ex)
    {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  public int pageNumber(HttpServletRequest request) {
    if (request.getParameter(Parameter.PAGE) != null) {
      return Integer.parseInt(request.getParameter(Parameter.PAGE));
    } else {
      return 1;
    }
  }

  public String[] parameterValues(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split(Url.PATH_SEPERATOR);
  }

  public int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = parameterValues(request);
    return Integer.parseInt(paths[index]);
  }

}
