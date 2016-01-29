package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.constants.UrlConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */

@WebServlet({ "/users/*" })
public class UserController extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
  UserService userService = new UserService();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String path = request.getPathInfo();

    try {
      if (path == null) {
        request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_SIGN_UP).forward(request, response);
      }
      else {
        String[] parts = path.split(UrlConstants.PATH_SEPARATOR);
        if (AppConstants.CREATE.equals(parts[1])) {
          createProcess(request, response);
        }
        else if (AppConstants.EDIT.equals(parts[1])) {
          request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_HOME_PAGE).forward(request,
            response);
        }
        else if (AppConstants.SIGN_IN.equals(parts[1])) {
            authenticateUser(request, response);
        }
        else if (AppConstants.LOG_OUT.equals(parts[1])) {
          HttpSession session = request.getSession();
          session.removeAttribute(AttributeConstants.USER);
          request.setAttribute(AppConstants.LOG_OUT_SUCCESS_MESSAGE, AppConstants.PAGE_NOT_FOUND_MESSAGE);
          response.sendRedirect(request.getContextPath() + UrlConstants.USER_SIGN_IN_ROUTE);
        }
      }
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.ERROR_MESSAGE, e.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();
    try {
      if (path == null) {
        request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_HOME_PAGE).forward(request, response);
      }
      else {
        String[] parts = path.split(UrlConstants.PATH_SEPARATOR);
        if (AppConstants.SIGN_UP.equals(parts[1])) {
          request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_SIGN_UP).forward(request, response);
        }
        else if (AppConstants.SIGN_IN.equals(parts[1])) {
          redirectAuthenticatedUser(request, response);
        }
      }
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.ERROR_MESSAGE, e.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }

  private void fetch(HttpServletRequest request, HttpServletResponse response) throws
    ServletException,
    IOException {
    response.sendRedirect(request.getContextPath() + UrlConstants.USER_ROUTE);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      User user = setUserAttributes(request);
      userService.create(user);
      fetch(request, response);
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(AttributeConstants.ERROR_MESSAGE, e.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private void authenticateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, DataException {
    User user = setUserAttributesForSignIn(request);
    User authenticatedUser = userService.fetchUserByUsernameAndPassword(user);
    if (authenticatedUser != null) {
      HttpSession session = request.getSession();
      session.setAttribute(AttributeConstants.USER, authenticatedUser.getId());
      response.sendRedirect(request.getContextPath() + UrlConstants.USER_ROUTE);
    }
    else {
      response.sendRedirect(request.getContextPath() + UrlConstants.USER_SIGN_IN_ROUTE);
    }
  }

  private User setUserAttributes (HttpServletRequest request) {
    String name = request.getParameter(AttributeConstants.NAME);
    String username = request.getParameter(AttributeConstants.USERNAME);
    String email = request.getParameter(AttributeConstants.EMAIL);
    String password = request.getParameter(AttributeConstants.PASSWORD);

    User user = new User();
    user.setName(name);
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(password);

    return user;
  }

  private User setUserAttributesForSignIn (HttpServletRequest request) {
    String username = request.getParameter(AttributeConstants.USERNAME);
    String password = request.getParameter(AttributeConstants.PASSWORD);

    User user = new User();
    user.setUsername(username);
    user.setPassword(password);

    return user;
  }

  private void redirectAuthenticatedUser(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    HttpSession session = request.getSession();
    if (session.getAttribute(AttributeConstants.USER) == null) {
      request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_SIGN_IN).forward(request,
        response);
    }
    else {
      response.sendRedirect(request.getContextPath() + UrlConstants.USER_ROUTE);
    }
  }
}

