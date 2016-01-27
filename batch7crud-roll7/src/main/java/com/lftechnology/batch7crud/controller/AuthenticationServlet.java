package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leapfrog on 1/27/16.
 */
@WebServlet(name = "AuthenticationServlet", urlPatterns = { "/authenticate" })
public class AuthenticationServlet extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("name");
    String password = request.getParameter("password");

    UserService userService = new UserService();

    try {
      User user = userService.fetch(username, password);
      if (user != null) {
        HttpSession session = request.getSession();
        session.setAttribute("user", username);
        response.sendRedirect(request.getContextPath() + "/");
      } else {
        request.getRequestDispatcher(CommonConstants.LOG_IN_VIEW).forward(request, response);
      }

    } catch (DataException | ServletException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

  }

}
