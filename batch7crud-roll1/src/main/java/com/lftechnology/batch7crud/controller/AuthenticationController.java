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

import static com.lftechnology.batch7crud.constant.URLConstant.*;
import static com.lftechnology.batch7crud.constant.AttributeConstant.*;

/**
 * This Class handles login logout function
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/27/16.
 */
@WebServlet({ "/authentication/*" })
public class AuthenticationController extends CustomHttpServlet {

  private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      String[] pathParts = getPathParameters(request);
      if (pathParts.length == 3 && LOGIN.equals(pathParts[2])) {
        loginProcess(request, response);
      } else if (pathParts.length == 3 && LOGOUT.equals(pathParts[2])) {
        logoutProcess(request, response);
      } else {
        showPageNotFound(request, response);
      }
    } catch (ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String[] pathParts = getPathParameters(request);
      if (pathParts.length == 3 && LOGIN.equals(pathParts[2])) {
        login(request, response);
      } else {
        showPageNotFound(request, response);
      }
    } catch (ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
  }

  private void loginProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    UserService userService = new UserService();

    try {

      if (userService.isValidUser(userName, password)) {
        HttpSession session = request.getSession();
        session.setAttribute("user", "user");
        response.sendRedirect(request.getContextPath() + "/");

      } else {
        request.setAttribute(ERRORS, "invalid username or password");
        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(LOGIN_PAGE);
        requestDispatcher.forward(request, response); //NOSONAR
      }
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }

  }

  private void logoutProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.invalidate();
    RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(LOGIN_PAGE);
    requestDispatcher.forward(request, response); //NOSONAR
  }
}
