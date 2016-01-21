package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */

@WebServlet({ "/users/*" })
public class UsersController extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(User.class.getName());

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();

    if (path == null) {
      request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
    }
    else {
      String[] parts = path.split("/");
      if ("create".equals(parts[1])) {
        create(request, response);
      }
      else if ("update".equals(parts[1])) {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(request, response);
      }
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();

    if (path == null) {
      request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(request,
        response);
    }
    else {
      String[] parts = path.split("/");
      if ("signup".equals(parts[1])) {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
      }
      else if ("signin".equals(parts[1])) {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/signin.jsp").forward(request, response);
      }
    }
  }

  private void fetch(HttpServletResponse response) throws
    ServletException,
    IOException {
    response.sendRedirect("/users");
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String name = request.getParameter("name");
      String username = request.getParameter("username");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      UserService userService = new UserService();

      User user = new User();
      user.setName(name);
      user.setUsername(username);
      user.setEmail(email);
      user.setPassword(password);

      userService.create(user);
      fetch(response);
    }
    catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }
}

