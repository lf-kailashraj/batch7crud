package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/26/16.
 */
@WebServlet(name = "UserAuthenticationServlet", urlPatterns = { "/authenticate/*" })
public class UserAuthenticationServlet extends CommonHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(UserAuthenticationServlet.class.getName());

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] pathParts = getPathParameters(request);

    if (pathParts.length == 3 && AppConstants.LOGIN.equals(pathParts[2])) {
      login(request, response);
    } else {
      displayPageNotFound(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] pathParts = getPathParameters(request);

    if (pathParts.length == 3 && AppConstants.LOGIN.equals(pathParts[2])) {
      loginProcess(request, response);
    } else if (pathParts.length == 3 && AppConstants.LOGOUT.equals(pathParts[2])) {
      logoutProcess(request, response);
    } else {
      displayPageNotFound(request, response);
    }
  }

  private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (request.getSession().getAttribute(AttributeConstants.USER) == null)
      request.getServletContext().getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
    else
      response.sendRedirect(request.getContextPath() + UrlConstants.INDEX_ROUTE);
  }

  private void loginProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String userName = request.getParameter(AttributeConstants.USERNAME);
    String password = request.getParameter(AttributeConstants.USER_PASSWORD);
    UserService adminService = new UserService();

    try {
      if (adminService.isValidUser(userName, password)) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeConstants.USER, userName);
        response.sendRedirect(request.getContextPath() + UrlConstants.INDEX_ROUTE);
      } else {
        request.setAttribute(AttributeConstants.LOGIN_ERROR, "Username/Password Incorrect");
        request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
      }
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      displayErrorPage(request, response, e.getMessage());
    }
  }

  private void logoutProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    response.sendRedirect(request.getContextPath() + UrlConstants.LOGIN_ROUTE);
  }
}
