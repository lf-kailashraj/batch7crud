package com.lftechnology.batch7crud.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by achyut on 2/2/16.
 */
@WebServlet(name = "LogoutController", value = {"/logout"})
public class LogoutController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session != null){
      session.invalidate();
    }
    response.sendRedirect("/login");
  }
}
