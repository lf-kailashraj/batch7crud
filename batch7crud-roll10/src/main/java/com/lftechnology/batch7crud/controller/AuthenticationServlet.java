package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constant.PageConstant;
import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author binodnme
 * Created on 1/27/16
 */
@WebServlet("/auth/*")
public class AuthenticationServlet extends CustomHttpServlet{
  private static AuthenticationService authenticationService = new AuthenticationService();
  private static final Logger LOGGER = Logger.getLogger(AuthenticationServlet.class.getName());
  private static final String USER = "user";
  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String[] params = params(req);

    if(PageConstant.LOGIN.equalsIgnoreCase(params[1])){
      req.getServletContext().getRequestDispatcher(PageConstant.LOGIN_VIEW).forward(req, resp);
    }else if(PageConstant.LOGOUT.equalsIgnoreCase(params[1])){
      HttpSession session = req.getSession();
      session.removeAttribute(USER);
      resp.sendRedirect(PageConstant.LOGIN_URL);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String username = req.getParameter(USERNAME);
    String password = req.getParameter(PASSWORD);

    User user = null;
    try {
      user = validateLogin(username, password);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

    if (user == null){
      req.getRequestDispatcher(PageConstant.LOGIN_VIEW).forward(req, resp);
    }
    else{
      HttpSession session = req.getSession();
      session.setAttribute(USER, user);
      resp.sendRedirect(PageConstant.STUDENT_LIST_URL);
    }

  }

  private User validateLogin(String username, String password) throws DataException {

    if(username!=null && !username.isEmpty() && password!=null && !password.isEmpty()){
      return authenticationService.isValid(username, password);
    }

    return null;
  }
}
