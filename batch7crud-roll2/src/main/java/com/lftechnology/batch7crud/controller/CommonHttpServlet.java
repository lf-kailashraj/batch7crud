package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
public abstract class CommonHttpServlet extends HttpServlet {

  protected void displayPageNotFound(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    req.setAttribute(AttributeConstants.ERROR_MESSAGE, AppConstants.PAGE_NOT_FOUND_MESSAGE);
    req.getRequestDispatcher(UrlConstants.PAGE_NOT_FOUND).forward(req, res);
  }

  protected void displayErrorPage(HttpServletRequest req, HttpServletResponse res, String message) throws ServletException, IOException {
    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    req.setAttribute(AttributeConstants.ERROR_MESSAGE, message);
    req.getRequestDispatcher(UrlConstants.ERROR_PAGE).forward(req, res);
  }

  protected String[] getPathParameters(HttpServletRequest request) {
    String urlPath = request.getRequestURI().substring(request.getContextPath().length());
    return urlPath.split("/");
  }

  protected int parameterValueAsInt(HttpServletRequest request, int index) {
    String[] paths = getPathParameters(request);
    return Integer.parseInt(paths[index]);
  }

  protected Map<String, String> mapParameters(HttpServletRequest request) {
    Map<String, String> inputs = new HashMap<>();
    inputs.put(AttributeConstants.NAME, request.getParameter(AttributeConstants.NAME));
    inputs.put(AttributeConstants.ADDRESS, request.getParameter(AttributeConstants.ADDRESS));
    inputs.put(AttributeConstants.EMAIL, request.getParameter(AttributeConstants.EMAIL));
    inputs.put(AttributeConstants.CONTACT, request.getParameter(AttributeConstants.CONTACT));
    return inputs;
  }
}
