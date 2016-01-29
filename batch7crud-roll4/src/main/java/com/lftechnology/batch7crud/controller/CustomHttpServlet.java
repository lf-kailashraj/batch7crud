package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.util.CheckParameter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lftechnology.batch7crud.constant.ActionConstants.*;
import static com.lftechnology.batch7crud.constant.AttribConstants.ATTRIB_MESSAGE;
import static com.lftechnology.batch7crud.constant.MessageConstants.MESSAGE_PAGE_NOT_FOUND;
import static com.lftechnology.batch7crud.constant.ParamConstants.PARAM_PAGE;
import static com.lftechnology.batch7crud.constant.UrlConstants.URL_ERROR_PAGE;

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
    String[] parameters = parameterValues(request);
    return Integer.parseInt(parameters[index]);
  }

  public void validateUrl(HttpServletRequest request) throws ServletException { //NOSONAR
    String[] parameters = parameterValues(request);
    boolean isValid = false;

    switch (parameters.length) {
    case 2:
      isValid = true;
      break;
    case 3:
      if (ACTION_CREATE.equals(parameters[2]) || CheckParameter.isInt(parameters[2]) || ACTION_LOGIN.equals(parameters[2]) || ACTION_LOGOUT
              .equals(parameters[2])) {
        isValid = true;
      }
      break;
    case 4:
      if (ACTION_EDIT.equals(parameters[3]) || ACTION_DELETE.equals(parameters[3])) {
        isValid = true;
      }
      break;
    default:
      isValid = false;
      break;
    }

    if (!isValid) {
      throw new ServletException(MESSAGE_PAGE_NOT_FOUND);
    }

  }

  public String fetchActionFromParameter(HttpServletRequest request) {
    String[] parameters = parameterValues(request);
    String action;

    if (parameters.length == 2) {
      action = ACTION_LIST;
    } else if (parameters.length == 3 && CheckParameter.isInt(parameters[2])) {
      action = ACTION_PROFILE;
    } else {
      action = parameters[parameters.length - 1];
    }

    return action;
  }
}
