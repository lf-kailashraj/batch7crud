package com.lftechnology.batch7crud.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserValidator
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.batch7crud.entity.User;

public class UserValidator extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    doPost(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    RequestDispatcher rd;
    String name = req.getParameter("name");
    String password = req.getParameter("password");

    System.out.println("Validating...");
    boolean isValid = validateLogin(name, password);

    if (!isValid) {
      rd = req.getRequestDispatcher("/loginError.jsp");
    } else {
      HttpSession session = req.getSession();
      session.setAttribute("user", new User("admin", "admin"));
      rd = req.getRequestDispatcher("/loginSuccess.jsp");
      System.out.println("Success");
    }

    rd.forward(req, res);
  }

  /**
   * Validate the entered data
   *
   * If there is no valid data, the method will return null
   *
   * @param name
   *          given at the jsp
   * @param password
   *          given at the jsp
   * @return a user if one was found and validated
   */
  private boolean validateLogin(String name, String password) {
    // All parameters must be valid
    if (name == null || password == null) {
      return false;
    }

    if ("admin".equals(password.trim())) {
      return false;
    }

    return true;
  }
}