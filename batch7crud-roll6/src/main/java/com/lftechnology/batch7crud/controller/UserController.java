package com.lftechnology.batch7crud.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import com.lftechnology.batch7crud.constant.ApplicationConstant;
import com.lftechnology.batch7crud.constant.CommonConstant;
import com.lftechnology.batch7crud.constant.URLConstants;
import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.service.UserService;
import com.lftechnology.batch7crud.util.TypeCaster;

/**
 * 
 * @author madandhungana <madandhungana@lftechnology.com> Jan 18, 2016
 */

@WebServlet("/users/*")
public class UserController extends CustomHttpServlet {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
  private static final int LIMIT = 10;

  private UserService userService = new UserService(); // NOSONAR

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String[] pathArgs = pathArgs(request);

      if (pathArgs.length <= 1) {
        int page = 1;
        String arg = request.getParameter(CommonConstant.PAGE);
        if (arg != null) {
          page = Integer.parseInt(arg);
        }
        this.list(request, response, page);
      } else {

        if (CommonConstant.EMPTY_STRING.equals(pathArgs[0]) && CommonConstant.ADD.equals(pathArgs[1])) {

          create(request, response);

        } else if (CommonConstant.EMPTY_STRING.equals(pathArgs[0]) && CommonConstant.EDIT.equals(pathArgs[2])) {
          int userID = TypeCaster.toInt(pathArgs[1]);
          edit(request, response, userID);

        }
      }
    } catch (ServletException | HTTPException | IOException | NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String urlString = request.getPathInfo();
      if (urlString == null) {

        this.list(request, response, 1);

      } else {
        String[] pathArgs = urlString.split(File.separator);
        if (CommonConstant.EMPTY_STRING.equals(pathArgs[0]) && CommonConstant.ADD.equals(pathArgs[1])) {
          createProcess(request, response);

        } else if (CommonConstant.EMPTY_STRING.equals(pathArgs[0]) && CommonConstant.EDIT.equals(pathArgs[2])) {

          int userID = TypeCaster.toInt(pathArgs[1]);
          editProcess(request, response, userID);

        } else if (CommonConstant.EMPTY_STRING.equals(pathArgs[0]) && CommonConstant.DELETE.equals(pathArgs[2])) {

          int userID = TypeCaster.toInt(pathArgs[1]);
          deleteProcess(request, response, userID);

        }

      }
    } catch (ServletException | HTTPException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

  }

  private void list(HttpServletRequest request, HttpServletResponse response, int page) throws ServletException, IOException {
    try {
      int totalUser = userService.totalUser();
      int noOfPages = (int) Math.ceil(totalUser * 1.0 / LIMIT);
      if (page < 1 || page > noOfPages) {
        response.sendRedirect(ApplicationConstant.USER_LIST);
      }
      int offset = (page - 1) * LIMIT;
      request.setAttribute(CommonConstant.USERS, userService.fetch(offset, LIMIT));
      request.setAttribute(CommonConstant.CURRENT_PAGE, page);
      request.setAttribute(CommonConstant.NO_OF_PAGES, noOfPages);
      request.setAttribute(CommonConstant.LIMIT, LIMIT);
      request.getRequestDispatcher(URLConstants.LIST_USER).forward(request, response);

    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    } catch (IllegalStateException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

    }
  }

  private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(URLConstants.ADD_USER).forward(request, response);
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    User user = new User();
    Validator validator = new Validator();
    
    Map<String, String> errors = validator.validate(request);
    
    String firstName = request.getParameter(UserConstants.FIRST_NAME);
    String surName = request.getParameter(UserConstants.SUR_NAME);
    String username = request.getParameter(UserConstants.USERNAME);
    String password = request.getParameter(UserConstants.PASSWORD);

    user.setFirstName(firstName);
    user.setSurName(surName);
    user.setUserName(username);
    user.setPassword(password);

    if (errors.isEmpty()) {
      try {
        userService.addUser(user);
        response.sendRedirect(ApplicationConstant.USER_LIST);
      } catch (DataException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
      request.setAttribute("msz", null);
    } else {
      request.setAttribute("user", user);
      request.setAttribute("errors", errors);
      request.getRequestDispatcher(URLConstants.ADD_USER).forward(request, response);

    }

  }

  private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
    try {
      request.setAttribute(CommonConstant.USER, userService.fetchByID(id));
      request.getRequestDispatcher(URLConstants.EDIT_USER).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {

    User user = new User();

    String firstName = request.getParameter(UserConstants.FIRST_NAME);
    String surName = request.getParameter(UserConstants.SUR_NAME);
    String username = request.getParameter(UserConstants.USERNAME);

    user.setFirstName(firstName);
    user.setSurName(surName);
    user.setUserName(username);
    user.setId(id);

    try {
      userService.update(user);
      response.sendRedirect(ApplicationConstant.USER_LIST);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
    try {
      userService.deleteUser(id);
      response.sendRedirect(ApplicationConstant.USER_LIST);

    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      showServerError(request, response, e);
    }

  }



}
