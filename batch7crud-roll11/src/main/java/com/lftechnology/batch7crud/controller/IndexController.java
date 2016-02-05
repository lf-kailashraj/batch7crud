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

@WebServlet({"/index"})
public class IndexController extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(IndexController.class.getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      HttpSession session = req.getSession();
      String userName = (String) session.getAttribute("user");
      req.setAttribute("userName", userName);
      req.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }catch (ServletException | IOException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }
}