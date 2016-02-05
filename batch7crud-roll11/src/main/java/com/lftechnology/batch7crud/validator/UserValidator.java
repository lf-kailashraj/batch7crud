package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by achyut on 2/2/16.
 */
public class UserValidator implements Validator<User> {
  Map<String, String> errors = null;

  @Override
  public void validateEmpty(Map<String, String> inputs) throws ValidationException {
    errors = new HashMap<>();
    if(StringUtil.isEmpty(inputs.get(UserConstants.USER_NAME))){
      errors.put(UserConstants.USER_NAME, "Username cannot be empty.");
    }
    if(StringUtil.isEmpty(inputs.get(UserConstants.PASSWORD))){
      errors.put(UserConstants.PASSWORD, "Password cannot be empty.");
    }

    if(!errors.isEmpty()){
      throw new ValidationException(errors);
    }
  }

  @Override
  public void validateBusinessLogic(User user) throws ValidationException {
    //not implemented
  }
}
