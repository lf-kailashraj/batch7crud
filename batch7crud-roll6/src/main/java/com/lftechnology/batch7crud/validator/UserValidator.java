/**
 * 
 */
package com.lftechnology.batch7crud.validator;

import java.util.HashMap;
import java.util.Map;

import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.util.NumberUtils;
import com.lftechnology.batch7crud.util.StringUtil;

/*
 * @author madandhungana <madandhungana@lftechnology.com>
 *  Jan 23, 2016
 *   batch7crud-roll6
 * 
 */
public class UserValidator implements Validator<User> {
  Map<String, String> errors = new HashMap<>();

  @Override
  public void validate(User user) throws ValidationException {
    String onlyCharRegex = "[a-zA-Z]*";
    if (!user.getFirstName().matches(onlyCharRegex)) {
      errors.put(UserConstants.FIRST_NAME, "First name cannot contain numeric value or special character");
    }
    if (!user.getSurName().matches(onlyCharRegex)) {
      errors.put(UserConstants.SUR_NAME, "Surname name cannot contain numeric value or special character");
    }

    if (user.getAge() > 80) {
      errors.put(UserConstants.AGE, "Age cannot be greater than 80");
    }
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }

  }

  public void emptyValidate(Map<String, String> userAttributes) throws ValidationException {

    if (StringUtil.isEmptyOrNull(userAttributes.get(UserConstants.FIRST_NAME).trim())) {
      errors.put(UserConstants.FIRST_NAME, "Please enter your first name");
    }
    if (StringUtil.isEmptyOrNull(userAttributes.get(UserConstants.SUR_NAME).trim())) {
      errors.put(UserConstants.SUR_NAME, "Please enter your Surname name");
    }
    if (StringUtil.isEmptyOrNull(userAttributes.get(UserConstants.USERNAME).trim())) {
      errors.put(UserConstants.USERNAME, "Please enter your user name");
    }

    if (StringUtil.isEmptyOrNull(userAttributes.get(UserConstants.AGE))) {
      errors.put(UserConstants.AGE, "Please enter your age");
    }
    if (!StringUtil.isEmptyOrNull(userAttributes.get(UserConstants.AGE)) && !NumberUtils.isNumeric(userAttributes.get(UserConstants.AGE))) {

      errors.put(UserConstants.AGE, "Age should be a number");
    }
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }

  }

}
