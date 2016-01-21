package com.lftechnology.batch7crud.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/19/16
 */

public abstract class CustomHttpServlet extends HttpServlet {
  private static final String ERROR_MESSAGE = "errorMessage";
  private static final String ERROR_PAGE = "/WEB-INF/views/error.jsp";

  protected void showPageNotFound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute(ERROR_MESSAGE, "page not found");
    req.getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
  }

  protected void showServerError(HttpServletRequest req, HttpServletResponse resp, Throwable e) throws ServletException, IOException {
    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    req.setAttribute(ERROR_MESSAGE, e.getMessage());
    req.getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
  }

  public String[] params(HttpServletRequest req) {
    String servletPath = req.getPathInfo();
    if (servletPath == null) {
      return new String[] {};
    } else {
      return servletPath.split("/");
    }
  }
}
