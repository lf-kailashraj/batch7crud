package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.URLConstant.*;
import static com.lftechnology.batch7crud.constant.AttributeConstant.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom HttpServlet that handles the common operations of all servlets
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/18/16
 */

public abstract class CustomHttpServlet extends HttpServlet {

  protected void showPageNotFound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute(ERROR_MESSAGE, "page not found");
    req.getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
  }

  protected void showServerError(HttpServletRequest req, HttpServletResponse resp, Throwable e) throws ServletException, IOException {
    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    req.setAttribute(ERROR_MESSAGE, e.getMessage());
    req.getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
  }

  protected String[] getPathParameters(HttpServletRequest req) {
    String urlPath = req.getRequestURI().substring(req.getContextPath().length());
    return urlPath.split("/");
  }

  protected int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = getPathParameters(request);
    return Integer.parseInt(paths[index]);
  }
}
