package com.lftechnology.batch7crud.controller;

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
}
