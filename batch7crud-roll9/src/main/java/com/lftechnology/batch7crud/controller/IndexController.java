package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.AppConstant;
import com.lftechnology.batch7crud.constants.PageConstant;
import com.lftechnology.batch7crud.constants.ParameterConstant;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/home"})
public class IndexController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String username = session.getAttribute(AppConstant.USER).toString();
    request.setAttribute(ParameterConstant.USERNAME,username);
    request.getServletContext().getRequestDispatcher(PageConstant.INDEX_PAGE).forward(request, response);
  }
}