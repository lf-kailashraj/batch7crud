package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/21/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {
  protected String[] getPathParams(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split(UrlConstants.PATH_SEPARATOR);
  }

  protected int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = getPathParams(request);
    return Integer.parseInt(paths[index]);
  }

  protected String getAction(HttpServletRequest request) {
    String[] parts = getPathParams(request);

    if (parts.length == 0) {
      return AppConstants.FETCH;
    }
    else if (parts.length == 3 && AppConstants.CREATE.equals(parts[2])) {
      return AppConstants.CREATE;
    }
    else if (parts.length == 3) {
      return AppConstants.VIEW;
    }
    else if (parts.length == 4 && AppConstants.EDIT.equals(parts[3])) {
      return AppConstants.EDIT;
    }
    else {
      return null;
    }
  }
}
