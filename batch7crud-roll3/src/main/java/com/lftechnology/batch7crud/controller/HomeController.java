package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.CommonConstant.INDEX_PAGE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomeController extends CustomHttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
  }
}
