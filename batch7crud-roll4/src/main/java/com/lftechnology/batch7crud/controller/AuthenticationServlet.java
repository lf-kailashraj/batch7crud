package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.RouteConstants.ROUTE_EMPLOYEES;
import static com.lftechnology.batch7crud.constant.UrlConstants.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by pratishshr on 1/26/16.
 */
@WebServlet(name = "AuthenticationServlet", urlPatterns = { "/authenticate" })
public class AuthenticationServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username.equals("admin") && password.equals("admin")) {
      HttpSession session = request.getSession();
      session.setAttribute("username", username);
      response.sendRedirect(request.getContextPath() + ROUTE_EMPLOYEES);
    } else {
      RequestDispatcher view = request.getRequestDispatcher(VIEW + "login.jsp");
      view.forward(request, response);
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
