package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.CommonConstant.CREATE;
import static com.lftechnology.batch7crud.constant.CommonConstant.DELETE;
import static com.lftechnology.batch7crud.constant.CommonConstant.EDIT;
import static com.lftechnology.batch7crud.constant.CommonConstant.LIST;
import static com.lftechnology.batch7crud.constant.CommonConstant.PAGE_NOT_FOUND;
import static com.lftechnology.batch7crud.constant.CommonConstant.PAGE_NUMBER;
import static com.lftechnology.batch7crud.constant.CommonConstant.SHOW;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.lftechnology.batch7crud.utils.ValidatorUtil;

/**
 * This is an abstract class which is extended by servlets. It contains commonly
 * used methods by other servlets.
 * 
 * @author bishal
 *
 */
public abstract class CustomHttpServlet extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(CustomHttpServlet.class.getName());

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

  protected String getAction(HttpServletRequest request) {
    String[] parameters = parameterValues(request);
    String action;
    if (parameters.length == 2)
      action = LIST;
    else if (parameters.length == 3 && ValidatorUtil.isInteger(parameters[2]))
      action = SHOW;
    else
      action = parameters[parameters.length - 1];
    return action;
  }

  protected void validateURL(HttpServletRequest request) throws ServletException {
    String[] parameters = parameterValues(request);
    boolean isValid;

    if ("authentication".equals(parameters[1])) {
      isValid = validateAuthURL(parameters);
    } else if (parameters.length == 2) {
      isValid = true;
    } else {
      isValid = validateCrudURL(parameters);
    }

    if (!isValid) {
      throw new ServletException(PAGE_NOT_FOUND);
    }
  }

  private boolean validateCrudURL(String[] parameters) {
    switch (parameters.length) {
    case 3:
      if (ValidatorUtil.isInteger(parameters[2]) || CREATE.equals(parameters[2]))
        return true;
      break;
    case 4:
      if (DELETE.equals(parameters[3]) || EDIT.equals(parameters[3]))
        return true;
      break;
    default:
      break;
    }
    return false;
  }

  private boolean validateAuthURL(String[] parameters) {
    if (parameters.length == 3 && ("login".equals(parameters[2]) || "logout".equals(parameters[2]))) {
      return true;
    }
    return false;
  }
}
