package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.constants.AttributeConstant;
import com.lftechnology.batch7crud.constants.MessageConstant;
import com.lftechnology.batch7crud.exception.ValidationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sanjay on 1/29/16.
 */
public class LoginValidator {
  public void validate(String username, String pass) throws ValidationException{
    Map<String, String> error = new HashMap<>();
    if(!isValid(username)){
      error.put(AttributeConstant.ERROR_UNAME, MessageConstant.EMPTY_FIELD);
    }
    if(!isValid(pass)){
      error.put(AttributeConstant.ERROR_PASSWORD, MessageConstant.EMPTY_FIELD);
    }
    if(!error.isEmpty()){
      ValidationException validationException = new ValidationException();
      validationException.setErrors(error);
      throw validationException;
    }
  }

  private boolean isValid(String username){
    return (username.isEmpty() || username==null)? false:true;
  }
}
