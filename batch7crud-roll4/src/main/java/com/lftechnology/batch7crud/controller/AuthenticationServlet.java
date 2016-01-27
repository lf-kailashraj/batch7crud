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

import static com.lftechnology.batch7crud.constant.RouteConstants.ROUTE_EMPLOYEES;
import static com.lftechnology.batch7crud.constant.UrlConstants.URL_LOGIN_PAGE;
import static com.lftechnology.batch7crud.constant.UrlConstants.VIEW;

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
    String action = fetchActionFromParameter(request);

    switch (action) {
    case "login":
      logIn(request, response);
      break;

    case "logout":
      logOut(request, response);
      break;

    default:
      show404(request, response);
      break;
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = fetchActionFromParameter(request);

    switch (action) {
    case "login":
      RequestDispatcher view = request.getRequestDispatcher(VIEW + "login.jsp");
      view.forward(request, response);
      break;

    case "logout":
      response.sendRedirect(request.getContextPath() + "/login.jsp");
      break;

    default:
      show404(request, response);
      break;
    }

  }

  private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      String username = request.getParameter("username");
      String password = request.getParameter("password");

      if (userService.isValidUser(username, password)) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        response.sendRedirect(request.getContextPath() + ROUTE_EMPLOYEES);
      } else {
        RequestDispatcher view = request.getRequestDispatcher(URL_LOGIN_PAGE);
        view.forward(request, response);
      }
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    }
  }

  private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.invalidate();
    response.sendRedirect(request.getContextPath() + "/" + "auth/login");
  }
}
