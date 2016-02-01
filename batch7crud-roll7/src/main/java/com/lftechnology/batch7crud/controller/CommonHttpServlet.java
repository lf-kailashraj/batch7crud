package com.lftechnology.batch7crud.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com> on 1/21/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {

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
