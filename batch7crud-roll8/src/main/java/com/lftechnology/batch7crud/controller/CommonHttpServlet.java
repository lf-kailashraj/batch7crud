package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.contants.AppConstants;
import com.lftechnology.batch7crud.contants.AttributeConstants;
import com.lftechnology.batch7crud.contants.UrlConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/21/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {

    protected void pageNotFound(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      request.setAttribute(AttributeConstants.ERROR_MESSAGE, AppConstants.PAGE_NOT_FOUND_MESSAGE);
      request.getRequestDispatcher(UrlConstants.PAGE_NOT_FOUND).forward(request, response);
    }

    protected void errorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException,
      IOException {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      request.setAttribute(AttributeConstants.ERROR_MESSAGE, message);
      request.getRequestDispatcher(UrlConstants.ERROR_PAGE).forward(request, response);
    }


  protected String[] getPathParams(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split("/");
  }

  protected int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = getPathParams(request);
    return Integer.parseInt(paths[index]);
  }
}
