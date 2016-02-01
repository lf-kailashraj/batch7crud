package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.*;
import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.services.LoginService;
import com.lftechnology.batch7crud.util.DataHash;
import com.lftechnology.batch7crud.util.LoginValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/29/16.
 */
@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
  private static LoginService loginService = new LoginService();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    LoginValidator loginValidator = new LoginValidator();
    Map<String, String> loginError = new HashMap<>();
    try {
      User user = requestToUserObject(request);
      request.setAttribute(AppConstant.USEROBJECT, user);
      String pwd = request.getParameter(ParameterConstant.PASSWORD);
      loginValidator.validate(user.getUsername(), pwd);
      if (loginService.authenticate(user)) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeConstant.USER, user.getUsername());
        response.sendRedirect(request.getContextPath() + File.separator + UrlConstant.HOME);
      } else {
        loginError.put(AttributeConstant.DATA_MISMATCH, MessageConstant.DATA_MISMATCH);
      }
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      loginError = e.getErrors();
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    if (!loginError.isEmpty()) {
      request.setAttribute(AttributeConstant.ERROR_LOGIN_DATA, loginError);
      request.getServletContext().getRequestDispatcher(PageConstant.LOGIN_PAGE).forward(request, response);
    }
  }

  private User requestToUserObject(HttpServletRequest request) {
    String username = request.getParameter(ParameterConstant.USERNAME);
    String pwd = request.getParameter(ParameterConstant.PASSWORD);
    User user = new User();
    user.setUsername(username);
    try {
      user.setPassword(DataHash.getMD5(pwd));
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return user;
  }
}
