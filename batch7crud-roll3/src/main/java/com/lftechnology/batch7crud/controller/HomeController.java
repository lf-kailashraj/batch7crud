package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.CommonConstant.INDEX_PAGE;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomeController extends CustomHttpServlet {
  private static final Logger LOGGER = Logger.getLogger(HomeController.class.getName());

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
    } catch (ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      show500(request, response, e);
    }
  }
}
