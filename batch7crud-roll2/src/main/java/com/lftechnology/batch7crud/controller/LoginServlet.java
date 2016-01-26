package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/26/16.
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final String userID = "admin";
  private final String password = "password";

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(req, resp);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // get request parameters for userID and password
    String user = request.getParameter("username");
    String pwd = request.getParameter("password");
    if (userID.equals(user) && password.equals(pwd)) {
      HttpSession session = request.getSession();
      session.setAttribute("user", "Romit");
      response.sendRedirect(request.getContextPath() + "/");
    } else {
      request.setAttribute("loginError", "Username/Password Incorrect");
      request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
    }
  }
}
