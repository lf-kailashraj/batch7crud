package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.Page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/"})
public class IndexController extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher(Page.INDEX).forward(request, response);
  }
}