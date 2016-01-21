package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constant.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is custom HttpServlet that is used to handle the common operations of all servlets.
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/19/16
 */
public abstract class CustomHttpServlet extends HttpServlet {

  protected void showPageNotFound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher(PageConstant.ERROR_PAGE).forward(req, resp);
  }

  protected void showServerError(HttpServletRequest req, HttpServletResponse resp, Throwable e) throws ServletException, IOException {
    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    req.setAttribute(PageConstant.ERROR_MESSAGE, e.getMessage());
    req.getServletContext().getRequestDispatcher(PageConstant.ERROR_PAGE).forward(req, resp);
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
