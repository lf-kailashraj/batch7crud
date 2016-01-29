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
public class AuthenticationController extends CustomHttpServlet{
  private static AuthenticationService authenticationService = new AuthenticationService();
  private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());
  private static final String USER = "user";
  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String action = paramsTest(req);

    switch (action){
    case PageConstant.LOGIN:
      login(req, resp);
      break;
    case PageConstant.LOGOUT:
      logout(req, resp);
      break;
    default:
      throw new ServletException(PageConstant.PAGE_NOT_FOUND);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String action = paramsTest(req);

    if(PageConstant.LOGIN.equalsIgnoreCase(action)){
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
        session.setAttribute(USER, user);                 //NOSONAR
        resp.sendRedirect(PageConstant.STUDENT_LIST_URL);
      }
    }
  }

  private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher(PageConstant.LOGIN_VIEW).forward(req, resp);
  }

  private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HttpSession session = req.getSession();
    session.removeAttribute(USER);
    resp.sendRedirect(PageConstant.LOGIN_URL);
  }

  private User validateLogin(String username, String password) throws DataException {

    if(username!=null && !username.isEmpty() && password!=null && !password.isEmpty()){
      return authenticationService.isValid(username, password);
    }

    return null;
  }
}
