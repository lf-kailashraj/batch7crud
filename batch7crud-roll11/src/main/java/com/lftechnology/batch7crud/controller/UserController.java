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
@WebServlet(name="UserControlelr", value={"/user/*"})
public class UserController extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    String [] parsedUrl = StringUtil.parseUrl(request.getRequestURI());
    try{
      if(parsedUrl.length == 3 && parsedUrl[2].equals(CommonConstants.ADD)){
        request.getRequestDispatcher(UrlConstants.ADD_USER).forward(request, response);
      }
    }catch (ServletException | HTTPException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    String [] parsedUrl = StringUtil.parseUrl(request.getRequestURI());

    try{
      if(parsedUrl.length == 3 && parsedUrl[2].equals(CommonConstants.ADD)){
        this.createProcess(request, response);
      }
    }catch (HTTPException | DataException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private void createProcess(HttpServletRequest request, HttpServletResponse response) throws DataException, ServletException, IOException {
    UserService userService = new UserService();
    Map<String, String> inputs = this.putInputToMap(request);
    UserValidator userValidator = new UserValidator();

    try {
      userValidator.validateEmpty(inputs);
      User user = new User(inputs);
      userService.create(user);
      response.sendRedirect(PathConstants.EMPLOYEE);
    }catch(ValidationException e){
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      request.setAttribute(CommonConstants.ERRORS, e.getErrors());
      request.getRequestDispatcher(UrlConstants.ADD_USER).forward(request, response);
    }
  }

  public Map<String, String> putInputToMap(HttpServletRequest request){
    Map<String, String> input = new HashMap<>();

    input.put(UserConstants.USER_NAME, request.getParameter(UserConstants.USER_NAME));
    input.put(UserConstants.PASSWORD, PasswordEncoder.encodePassword(request.getParameter(UserConstants.PASSWORD)));
    return input;
  }
}
