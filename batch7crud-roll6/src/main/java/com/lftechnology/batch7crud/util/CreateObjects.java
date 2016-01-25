package com.lftechnology.batch7crud.util;

import javax.servlet.http.HttpServletRequest;

import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.model.User;

public class CreateObjects {
  
  public User createUserObect(HttpServletRequest request){
    User user=new User();
    String firstName = request.getParameter(UserConstants.FIRST_NAME);
    String surName = request.getParameter(UserConstants.SUR_NAME);
    String username = request.getParameter(UserConstants.USERNAME);
    String password = request.getParameter(UserConstants.PASSWORD);

    user.setFirstName(firstName);
    user.setSurName(surName);
    user.setUserName(username);
    user.setPassword(password);
    return user;
  }

}
