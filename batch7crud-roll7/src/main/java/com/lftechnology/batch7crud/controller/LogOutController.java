package com.lftechnology.batch7crud.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leapfrog on 1/29/16.
 */
@WebServlet(name = "LogOutController",urlPatterns = {"/logOut"})
public class LogOutController extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(LogOutController.class.getName());

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    try{
      HttpSession session = request.getSession(false);
      if(session != null){
        session.invalidate();
      }
      response.sendRedirect(request.getContextPath());
    } catch(IOException e){
      LOGGER.log(Level.SEVERE,e.getMessage(),e);
    }
  }
}
