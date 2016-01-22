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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */

@WebServlet({ "/users/*" })
public class UsersController extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(User.class.getName());

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();

    if (path == null) {
      request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_SIGN_UP).forward(request, response);
    }
    else {
      String[] parts = path.split("/");
      if (AppConstants.CREATE.equals(parts[1])) {
        create(request, response);
      }
      else if (AppConstants.EDIT.equals(parts[1])) {
        request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_HOME_PAGE).forward(request, response);
      }
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();

    if (path == null) {
      request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_HOME_PAGE).forward(request, response);
    }
    else {
      String[] parts = path.split("/");
      if (AppConstants.SIGN_UP.equals(parts[1])) {
        request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_SIGN_UP).forward(request, response);
      }
      else if (AppConstants.SIGN_IN.equals(parts[1])) {
        request.getServletContext().getRequestDispatcher(request.getContextPath() + UrlConstants.USER_SIGN_IN).forward(request, response);
      }
    }
  }

  private void fetch(HttpServletRequest request, HttpServletResponse response) throws
    ServletException,
    IOException {
    response.sendRedirect(request.getContextPath() + UrlConstants.USER_ROUTE);
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String name = request.getParameter(AttributeConstants.NAME);
      String username = request.getParameter(AttributeConstants.USERNAME);
      String email = request.getParameter(AttributeConstants.EMAIL);
      String password = request.getParameter(AttributeConstants.PASSWORD);
      UserService userService = new UserService();

      User user = new User();
      user.setName(name);
      user.setUsername(username);
      user.setEmail(email);
      user.setPassword(password);

      userService.create(user);
      fetch(request, response);
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }
}

