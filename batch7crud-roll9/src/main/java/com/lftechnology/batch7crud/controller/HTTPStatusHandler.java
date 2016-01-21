package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.Message;
import com.lftechnology.batch7crud.constants.Page;
import com.lftechnology.batch7crud.constants.Parameter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sanjay on 1/19/16.
 */
public abstract class HTTPStatusHandler extends HttpServlet {

  private static final String MESSAGE = "";

  protected void show404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    request.setAttribute(MESSAGE, Message.PAGE_NOT_FOUND);

    RequestDispatcher view = request.getRequestDispatcher(Page.ERROR_PAGE);
    view.forward(request, response);
  }

  protected void show500(HttpServletRequest request, HttpServletResponse response, Throwable e) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    request.setAttribute(MESSAGE, e.getMessage());

    RequestDispatcher view = request.getRequestDispatcher(Page.ERROR_PAGE);
    view.forward(request, response);
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
    return urlPath.split("/");
  }

  public int parameterValueAsInt(HttpServletRequest request, int index) throws NumberFormatException {
    String[] paths = parameterValues(request);
    return Integer.parseInt(paths[index]);
  }

}
