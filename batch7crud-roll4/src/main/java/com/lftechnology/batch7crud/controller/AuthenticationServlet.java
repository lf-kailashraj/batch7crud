package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.lftechnology.batch7crud.constant.ActionConstants.*;
import static com.lftechnology.batch7crud.constant.MessageConstants.MESSAGE_INTERNAL_SERVER_ERROR;
import static com.lftechnology.batch7crud.constant.MessageConstants.MESSAGE_PAGE_NOT_FOUND;
import static com.lftechnology.batch7crud.constant.ParamConstants.PARAM_PASS;
import static com.lftechnology.batch7crud.constant.ParamConstants.PARAM_USERNAME;
import static com.lftechnology.batch7crud.constant.UrlConstants.URL_LOGIN_PAGE;

/**
 * Created by pratishshr on 1/26/16.
 */
@WebServlet(name = "AuthenticationServlet", urlPatterns = { "/auth/*" })
public class AuthenticationServlet extends CustomHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
  private UserService userService;

  public AuthenticationServlet() {
    userService = new UserService();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    validateUrl(request);
    String action = fetchActionFromParameter(request);

    switch (action) {
    case ACTION_LOGIN:
      logIn(request, response);
      break;

    case ACTION_LOGOUT:
      logOut(request, response);
      break;

    default:
      throw new ServletException(MESSAGE_PAGE_NOT_FOUND);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    validateUrl(request);
    String action = fetchActionFromParameter(request);

    switch (action) {
    case ACTION_LOGIN:
      RequestDispatcher view = request.getRequestDispatcher(URL_LOGIN_PAGE);
      view.forward(request, response);
      break;

    case ACTION_LOGOUT:
      response.sendRedirect(request.getContextPath() + "/" + "auth/login");
      break;

    default:
      throw new ServletException(MESSAGE_PAGE_NOT_FOUND);
    }

  }

  private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String username = request.getParameter(PARAM_USERNAME);
      String password = request.getParameter(PARAM_PASS);

      if (userService.isValidUser(username, password)) {
        HttpSession session = request.getSession();
        session.setAttribute(PARAM_USERNAME, username);
        response.sendRedirect(request.getContextPath() + ACTION_EMPLOYEES);
      } else {
        RequestDispatcher view = request.getRequestDispatcher(URL_LOGIN_PAGE);
        view.forward(request, response);
      }
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(MESSAGE_INTERNAL_SERVER_ERROR);
    }
  }

  private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.invalidate();
    response.sendRedirect(request.getContextPath() + "/" + "auth/login");
  }
}
