package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constants.UrlConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */

@WebServlet({ "/home" })
public class IndexController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher(UrlConstants.INDEX_PAGE).forward(req, resp);
  }
}