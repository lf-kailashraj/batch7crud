package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by sanjay on 1/29/16.
 */
@WebServlet({"/logout"})
public class LogoutServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(AppConstant.JSESSIONID)) {
          break;
        }
      }
    }
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    response.sendRedirect(request.getContextPath());
  }
}