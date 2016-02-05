package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.PageConstants;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/home"})
public class IndexController extends HttpServlet {
  private static final Logger logger = Logger.getLogger(StudentController.class.getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      req.getServletContext().getRequestDispatcher(PageConstants.INDEX_PAGE).forward(req, resp);
    } catch (ServletException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }
}