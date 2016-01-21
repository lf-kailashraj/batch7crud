package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lftechnology.batch7crud.constant.CommonConstant.*;

@WebServlet("/")
public class HomeController extends CustomHttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
    } catch (ServletException | IOException e) {
      show500(request, response, e);
    }
  }
}
