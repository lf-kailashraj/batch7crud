package com.lftechnology.batch7crud.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.service.UserService;
import com.lftechnology.batch7crud.util.TypeCaster;
import com.lftechnology.batch7crud.util.UserFactory;
import com.lftechnology.batch7crud.validator.PasswordValidator;
import com.lftechnology.batch7crud.validator.UserValidator;

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

    }catch(ArrayIndexOutOfBoundsException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
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

    Map<String, String> inputs = new HashMap<>();

    UserValidator userValidator = new UserValidator();
    PasswordValidator passwordValidator = new PasswordValidator();

    inputs.put(UserConstants.FIRST_NAME, request.getParameter(UserConstants.FIRST_NAME));
    inputs.put(UserConstants.SUR_NAME, request.getParameter(UserConstants.SUR_NAME));
    inputs.put(UserConstants.USERNAME, request.getParameter(UserConstants.USERNAME));
    inputs.put(UserConstants.AGE, request.getParameter(UserConstants.AGE));

    try {
      userValidator.emptyValidate(inputs);
      passwordValidator.isEmpty(request.getParameter(UserConstants.PASSWORD));
      inputs.put(UserConstants.PASSWORD, request.getParameter(UserConstants.PASSWORD));

      User user = UserFactory.createUserObect(inputs);
      userService.addUser(user);
      response.sendRedirect(ApplicationConstant.USER_LIST);
    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(CommonConstant.ERRORS, e.getErrors());
      request.getRequestDispatcher(URLConstants.ADD_USER).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

  }

  private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
    Map<String, String> userAttributes = new HashMap<>();
    try {
      User user = userService.fetchByID(id);

      userAttributes.put(UserConstants.UID, Integer.toString(user.getId()));
      userAttributes.put(UserConstants.FIRST_NAME, user.getFirstName());
      userAttributes.put(UserConstants.SUR_NAME, user.getSurName());
      userAttributes.put(UserConstants.USERNAME, user.getUserName());
      userAttributes.put(UserConstants.PASSWORD, user.getPassword());
      userAttributes.put(UserConstants.AGE, Integer.toString(user.getAge()));

      request.setAttribute("userAttributes", userAttributes);

      request.getRequestDispatcher(URLConstants.EDIT_USER).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void editProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {

    Map<String, String> userAttributes = new HashMap<>();

    UserValidator userValidator = new UserValidator();

    userAttributes.put(UserConstants.FIRST_NAME, request.getParameter(UserConstants.FIRST_NAME));
    userAttributes.put(UserConstants.SUR_NAME, request.getParameter(UserConstants.SUR_NAME));
    userAttributes.put(UserConstants.USERNAME, request.getParameter(UserConstants.USERNAME));
    userAttributes.put(UserConstants.AGE, request.getParameter(UserConstants.AGE));
    
    userAttributes.put(UserConstants.UID, Integer.toString(id));
    try {
      userValidator.emptyValidate(userAttributes);
      User user = UserFactory.createUserObect(userAttributes);
      user.setId(id);
      userService.update(user);
      response.sendRedirect(ApplicationConstant.USER_LIST);

    } catch (ValidationException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      
      request.setAttribute(CommonConstant.ERRORS, e.getErrors());
      request.setAttribute("userAttributes", userAttributes);
      request.getRequestDispatcher(URLConstants.EDIT_USER).forward(request, response);
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
    try {
      userService.deleteUser(id);
      response.sendRedirect(ApplicationConstant.USER_LIST);

    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }

}
