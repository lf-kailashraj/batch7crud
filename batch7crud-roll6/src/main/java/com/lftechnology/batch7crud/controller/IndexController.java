package com.lftechnology.batch7crud.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constant.URLConstants;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 18, 2016
 */
@WebServlet("/index")
public class IndexController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    HttpSession session = request.getSession();
    String username = (String) session .getAttribute("user");
    request.setAttribute("username", username);
    request.getRequestDispatcher(URLConstants.INDEX_PAGE).forward(request, response);

  }

}
