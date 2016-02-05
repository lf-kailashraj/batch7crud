package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.PageConstants;
import com.lftechnology.batch7crud.constants.ParameterConstants;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sagarmatha on 2/1/16.
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
  static Map<String, String> loginError = new HashMap<String, String>();
  private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
  private static final long serialVersionUID = 1L;
  private static UserServiceImpl userServiceImpl = new UserServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      req.getServletContext().getRequestDispatcher(PageConstants.LOGIN_PAGE).forward(req, resp);
    } catch (ServletException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User user = userServiceImpl.getUser();
    String usr = request.getParameter("user");
    String pwd = request.getParameter("pwd");
    request.setAttribute("loginError", loginError);
    if (user.getUserName().equals(usr) && user.getPassWord().equals(pwd)) {
      HttpSession session = request.getSession();
      session.setAttribute(ParameterConstants.USER, user.getUserName());
      //setting session to expiry in 30 mins
      session.setMaxInactiveInterval(30 * 60);
      try {
        response.sendRedirect("/home");
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    } else {
      loginError.put("error", "invalid combination of username and password");
      request.getServletContext().getRequestDispatcher(PageConstants.LOGIN_PAGE).forward(request, response); // NOSONAR
    }

  }
}
