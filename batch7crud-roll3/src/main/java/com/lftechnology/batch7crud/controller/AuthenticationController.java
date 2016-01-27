package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constant.CommonConstant;
import com.lftechnology.batch7crud.constant.StudentConstant;
import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.AuthenticationService;

@WebServlet("/authentication/*")
public class AuthenticationController extends CustomHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);
    if (parameters.length == 3 && ("logout").equals(parameters[2])) {
      logout(request, response);
    } else {
      show404(request, response);
    }
  }

  private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.invalidate();
    request.getRequestDispatcher(CommonConstant.LOGIN_PAGE).forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] parameters = parameterValues(request);
    if (parameters.length == 3 && ("login").equals(parameters[2])) {
      login(request, response);
    } else {
      show404(request, response);
    }

  }

  private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String name = request.getParameter("name");
      String password = request.getParameter("password");

      User user = new User(name, password);
      boolean isValid = validateLogin(user);

      if (!isValid) {
        request.getRequestDispatcher(CommonConstant.LOGIN_PAGE).forward(request, response);
      } else {
        HttpSession session = request.getSession();
        session.setAttribute("user", user.getName());
        response.sendRedirect(request.getContextPath() + StudentConstant.STUDENT_LIST_CONTROLLER);
      }
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      show500(request, response, e);
    }

  }

  private boolean validateLogin(User user) throws DataException {
    AuthenticationService authService = new AuthenticationService();
    return authService.isValid(user);

  }
}