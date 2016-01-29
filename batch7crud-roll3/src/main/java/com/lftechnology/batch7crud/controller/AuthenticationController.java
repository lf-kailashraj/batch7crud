package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.CommonConstant.HOME;
import static com.lftechnology.batch7crud.constant.CommonConstant.INTERNAL_SERVER_ERROR;
import static com.lftechnology.batch7crud.constant.CommonConstant.LOGIN_PAGE;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.AuthenticationService;

@WebServlet("/authentication/*")
public class AuthenticationController extends CustomHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    validateAuthURL(request);
    String action = getAction(request);
    if (("logout").equals(action)) {
      logout(request, response);
    } else
      response.sendRedirect(request.getContextPath() + HOME);
  }

  private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.invalidate();
    request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    validateAuthURL(request);

    login(request, response);
  }

  private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String name = request.getParameter("name");
      String password = request.getParameter("password");

      User user = new User();
      user.setName(name);
      user.setPassword(password);

      boolean isValid = validateLogin(user);

      if (!isValid) {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
      } else {
        HttpSession session = request.getSession();
        session.setAttribute("user", user.getName());
        response.sendRedirect(request.getContextPath() + HOME);
      }
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new ServletException(INTERNAL_SERVER_ERROR);
    }
  }

  private boolean validateLogin(User user) throws DataException {
    AuthenticationService authService = new AuthenticationService();
    return authService.isValid(user);
  }
}