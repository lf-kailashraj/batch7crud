/**
 * 
 */
package com.lftechnology.batch7crud.validator;

import java.util.HashMap;
import java.util.Map;

import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.model.User;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 23, 2016 batch7crud-roll6
 * 
 */
public class UserValidator {
  Map<String, String> errors = new HashMap<>();

  public Map<String, String> validate(User user) {
    emptyValidate(user);
    nonEmptyValidate(user);
    return errors;
  }

  private void emptyValidate(User user) {

    if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
      errors.put(UserConstants.FIRST_NAME, "Please enter your first name");
    }
    if (user.getSurName() == null || user.getSurName().isEmpty()) {
      errors.put(UserConstants.SUR_NAME, "Please enter your surname");
    }
    if (user.getUserName() == null || user.getUserName().isEmpty()) {
      errors.put(UserConstants.USERNAME, "Please enter your username");
    }
    if (user.getPassword() == null || user.getPassword().isEmpty()) {
      errors.put(UserConstants.PASSWORD, "Please enter your password");
    }

  }

  private void nonEmptyValidate(User user) {

    String onlyCharRegex = "[a-zA-Z]*";
    if (!user.getFirstName().matches(onlyCharRegex)) {
      errors.put(UserConstants.FIRST_NAME, "First name cannot contain numeric value or special character");
    }
    if (!user.getSurName().matches(onlyCharRegex)) {
      errors.put(UserConstants.SUR_NAME, "Surname name cannot contain numeric value or special character");
    }

  }

}
