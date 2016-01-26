package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AttributeConstants;
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
  private final String userId = "admin";
  private final String password = "password";

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getSession().getAttribute(AttributeConstants.USER) == null)
      req.getServletContext().getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(req, resp);
    else
      resp.sendRedirect(req.getContextPath() + UrlConstants.INDEX_ROUTE);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String user = request.getParameter(AttributeConstants.USERNAME);
    String pwd = request.getParameter(AttributeConstants.PASSWORD);
    if (userId.equals(user) && password.equals(pwd)) {
      HttpSession session = request.getSession();
      session.setAttribute(AttributeConstants.USER, userId);
      response.sendRedirect(request.getContextPath() + UrlConstants.INDEX_ROUTE);
    } else {
      request.setAttribute(AttributeConstants.LOGIN_ERROR, "Username/Password Incorrect");
      request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
    }
  }
}
