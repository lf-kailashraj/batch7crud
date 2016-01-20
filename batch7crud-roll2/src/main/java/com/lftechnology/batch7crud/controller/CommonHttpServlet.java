package com.lftechnology.batch7crud.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {
  public static final String ERROR_MESSAGE = "message";

  protected void displayPageNotFound(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    req.setAttribute(ERROR_MESSAGE, "Page Not Found");
    req.getRequestDispatcher("/WEB-INF/views/pageNotFound.jsp").forward(req, res);
  }

  protected void displayErrorPage(HttpServletRequest req, HttpServletResponse res, String message) throws ServletException, IOException {
    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    req.setAttribute(ERROR_MESSAGE, message);
    req.getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, res);
  }

  protected String[] getPathParameters(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split("/");
  }

  protected int parameterValueAsInt(HttpServletRequest request, int index) throws NumberFormatException {
    String[] paths = getPathParameters(request);
    return Integer.parseInt(paths[index]);
  }
}
