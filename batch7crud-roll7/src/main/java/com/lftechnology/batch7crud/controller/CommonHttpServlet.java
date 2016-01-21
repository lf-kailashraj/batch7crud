package com.lftechnology.batch7crud.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by leapfrog on 1/21/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {

  protected void showErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
    view.forward(request, response);
  }

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
