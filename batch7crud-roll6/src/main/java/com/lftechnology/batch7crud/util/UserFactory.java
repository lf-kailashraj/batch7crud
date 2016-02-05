package com.lftechnology.batch7crud.util;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.controller.UserController;
import com.lftechnology.batch7crud.model.User;

public class UserFactory {

  private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

  private UserFactory() {

  }

  public static User createUserObect(Map<String, String> input) {
    User user = new User();

    try {

      String firstName = input.get(UserConstants.FIRST_NAME);
      String surName = input.get(UserConstants.SUR_NAME);
      String username = input.get(UserConstants.USERNAME);
      String password = input.get(UserConstants.PASSWORD);
      int age = Integer.parseInt(input.get(UserConstants.AGE));

      user.setFirstName(firstName);
      user.setSurName(surName);
      user.setUserName(username);
      user.setPassword(password);
      user.setAge(age);

    } catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return user;

  }
}
