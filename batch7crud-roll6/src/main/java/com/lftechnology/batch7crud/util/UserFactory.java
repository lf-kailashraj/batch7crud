package com.lftechnology.batch7crud.util;

import java.util.Map;

import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.User;

public class UserFactory {
  private UserFactory(){
    
  }
  public static User createUserObect(Map<String, String> input, Map<String, String> errors) throws ValidationException {

    try {

      User user = new User();

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

      return user;
    } catch (NumberFormatException e) {
      errors.put(UserConstants.AGE, "Age must be number");
      throw new ValidationException(errors);
    }

  }
}
