package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constant.CommonConstants;
import com.lftechnology.batch7crud.constant.PathConstants;
import com.lftechnology.batch7crud.constant.UrlConstants;
import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.service.UserService;
import com.lftechnology.batch7crud.util.PasswordEncoder;
import com.lftechnology.batch7crud.util.StringUtil;
import com.lftechnology.batch7crud.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by achyut on 2/2/16.
 */
@WebServlet(name="LoginController", value={"/login"})
public class LoginController extends HttpServlet{
  private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

  private UserService userService = new UserService();  //NOSONAR

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    try {
      request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
    }catch (HTTPException | ServletException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    Map<String, String> inputs = new HashMap<>();
    inputs.put(UserConstants.USER_NAME, request.getParameter(UserConstants.USER_NAME));
    inputs.put(UserConstants.PASSWORD, PasswordEncoder.encodePassword(request.getParameter(UserConstants.PASSWORD)));

    UserValidator userValidator = new UserValidator();
    try{
      userValidator.validateEmpty(inputs);
      User user = new User(inputs);
      if(userService.checkUser(user))
        System.out.println("User is logged in");
      else{
        request.setAttribute(CommonConstants.ERRORS, "Invalid credentials");
        request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
      }
    }catch (ValidationException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(CommonConstants.ERRORS, e);
      request.getRequestDispatcher(UrlConstants.LOGIN_PAGE).forward(request, response);
    }catch (HTTPException | DataException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //NOSONAR
    }


  }
}
