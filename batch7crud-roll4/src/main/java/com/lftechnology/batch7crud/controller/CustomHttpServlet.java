package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.MessageConstant.*;
import static com.lftechnology.batch7crud.constant.AttribConstants.*;
import static com.lftechnology.batch7crud.constant.UrlConstants.*;
import static com.lftechnology.batch7crud.constant.ParamConstants.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pratish Shrestha <pratishshrestha@lftechnology.com> on 1/19/16.
 */

/*
  Custom abstract Servlet that contains common methods that will be used by all the controllers
 */
public abstract class CustomHttpServlet extends HttpServlet {

  protected void show404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    request.setAttribute(ATTRIB_MESSAGE, MESSAGE_PAGE_NOT_FOUND);

    RequestDispatcher view = request.getRequestDispatcher(URL_ERROR_PAGE);
    view.forward(request, response);
  }

  protected void show500(HttpServletRequest request, HttpServletResponse response, Throwable e) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    request.setAttribute(ATTRIB_MESSAGE, e.getMessage());

    RequestDispatcher view = request.getRequestDispatcher(URL_ERROR_PAGE);
    view.forward(request, response);
  }

  public int pageNumber(HttpServletRequest request) {
    if (request.getParameter(PARAM_PAGE) != null) {
      return Integer.parseInt(request.getParameter(PARAM_PAGE));
    } else {
      return 1;
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

}
