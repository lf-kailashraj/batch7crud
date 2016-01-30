package com.lftechnology.batch7crud.validator;

import java.util.HashMap;
import java.util.Map;

import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.util.StringUtil;

public class PasswordValidator {
  private  Map<String, String> errors = new HashMap<>();


  public  void isEmpty(String password) throws ValidationException {
    if (StringUtil.isEmptyOrNull(password.trim())) {
      errors.put(UserConstants.PASSWORD, "Please enter your password");
    }
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }

  }

  public  void validateBusinessLogic(String password) throws ValidationException {
    if (password.length() < 6) {
      errors.put(UserConstants.PASSWORD, "Password most contain atleast 6 characters");
    }
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }

}
