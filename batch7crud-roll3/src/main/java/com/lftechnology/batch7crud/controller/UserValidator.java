package com.lftechnology.batch7crud.controller;

import static com.lftechnology.batch7crud.constant.StudentConstant.STUDENT_LIST_CONTROLLER;

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

import com.lftechnology.batch7crud.constant.StudentConstant;
import com.lftechnology.batch7crud.entity.User;

@WebServlet("/userValidator")
public class UserValidator extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    doPost(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    RequestDispatcher rd;
    String name = req.getParameter("name");
    String password = req.getParameter("password");

    System.out.println("Validating...");
    System.out.println(name);
    System.out.println(password);
    boolean isValid = validateLogin(name, password);

    if (!isValid) {
      rd = req.getRequestDispatcher("/loginError.jsp");
    } else {
      HttpSession session = req.getSession();
      session.setAttribute("user", new User("admin", "admin"));
      res.sendRedirect(req.getContextPath() + StudentConstant.STUDENT_LIST_CONTROLLER); // NOSONAR
      System.out.println("Success");
    }

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
    System.out.println(name);
    System.out.println(password);
    if ("admin".equals(password.trim())) {
      return true;
    }

    return false;
  }
}