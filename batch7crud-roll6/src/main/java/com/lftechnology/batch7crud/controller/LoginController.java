package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.constant.ApplicationConstant;
import com.lftechnology.batch7crud.constant.URLConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.service.UserService;
import com.lftechnology.batch7crud.util.PasswordEncoder;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 27, 2016 batch7crud-roll6 2016
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(URLConstants.LOGIN_PAGE).forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String username = request.getParameter("username");
    String password = PasswordEncoder.encodePassword(request.getParameter("password"));

    UserService userService = new UserService();

    try {
      User user = userService.fetchUser(username, password);
      if(user != null){
        HttpSession session = request.getSession();
        session.setAttribute("user", user.getUserName());
        response.sendRedirect(ApplicationConstant.INDEX);
      }else{
        response.sendRedirect(ApplicationConstant.LOGIN);
      }
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

}
