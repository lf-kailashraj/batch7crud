/**
 * 
 */
package com.lftechnology.batch7crud.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lftechnology.batch7crud.constant.UserConstants;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 23, 2016 batch7crud-roll6
 * 
 */
public class Validator {
  Map<String, String> errors = new HashMap<>();

  public Map<String, String> validate(HttpServletRequest request) {
    emptyValidate(request);
    nonEmptyValidate(request);
    return errors;
  }

  private void emptyValidate(HttpServletRequest request) {

    if (request.getParameter(UserConstants.FIRST_NAME) == null || request.getParameter(UserConstants.FIRST_NAME).isEmpty()) {
      errors.put(UserConstants.FIRST_NAME, "Please enter your first name");
    }
    if (request.getParameter(UserConstants.SUR_NAME) == null || request.getParameter(UserConstants.SUR_NAME).isEmpty()) {
      errors.put(UserConstants.SUR_NAME, "Please enter your surname");
    }
    if (request.getParameter(UserConstants.USERNAME) == null || request.getParameter(UserConstants.USERNAME).isEmpty()) {
      errors.put(UserConstants.USERNAME, "Please enter your username");
    }
    if (request.getParameter(UserConstants.PASSWORD) == null || request.getParameter(UserConstants.PASSWORD).isEmpty()) {
      errors.put(UserConstants.PASSWORD, "Please enter your password");
    }

  }

  private void nonEmptyValidate(HttpServletRequest request) {

    String onlyCharRegex = "[a-zA-Z]*";
    if (!request.getParameter(UserConstants.FIRST_NAME).matches(onlyCharRegex)) {
      errors.put(UserConstants.FIRST_NAME, "First name cannot contain numeric value or special character");
    }
    if (!request.getParameter(UserConstants.SUR_NAME).matches(onlyCharRegex)) {
      errors.put(UserConstants.SUR_NAME, "Surname name cannot contain numeric value or special character");
    }

  }

}
