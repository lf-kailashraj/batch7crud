package com.lftechnology.batch7crud.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constant.ApplicationConstant;

/**
 * @author madandhungana <madandhungana@lftechnology.com>
 * Feb 1, 2016
 * batch7crud-roll6
 * 2016
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet { 
  private static final long serialVersionUID = 1L; 
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    response.sendRedirect(ApplicationConstant.LOGIN);
  }

}
