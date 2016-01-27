/**
 * 
 */
package com.lftechnology.batch7crud.validator;

import java.util.Map;

import com.lftechnology.batch7crud.constant.UserConstants;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.util.StringUtil;

/*
 * @author madandhungana <madandhungana@lftechnology.com> Jan 23, 2016 batch7crud-roll6
 * 
 */
public class UserValidator implements Validator<User> {

  @Override
  public void validate(User user,Map<String, String> errors) {
    nonEmptyValidate(user,errors);
    
  }

  public void emptyValidate(Map<String, String> input,Map<String, String> errors) {

    StringUtil stringUtil= new StringUtil();
    
    if(stringUtil.isEmptyOrNull(input.get(UserConstants.FIRST_NAME))){
      errors.put(UserConstants.FIRST_NAME, "Please enter your first name");
    }
    if(stringUtil.isEmptyOrNull(input.get(UserConstants.SUR_NAME))){
      errors.put(UserConstants.SUR_NAME, "Please enter your Surname name");
    }
    if(stringUtil.isEmptyOrNull(input.get(UserConstants.USERNAME))){
      errors.put(UserConstants.USERNAME, "Please enter your user name");
    }
    if(stringUtil.isEmptyOrNull(input.get(UserConstants.PASSWORD))){
      errors.put(UserConstants.PASSWORD, "Please enter your password");
    }

  }


  private void nonEmptyValidate(User user,Map<String, String> errors) {

    String onlyCharRegex = "[a-zA-Z]*";
    if (!user.getFirstName().matches(onlyCharRegex)) {
      errors.put(UserConstants.FIRST_NAME, "First name cannot contain numeric value or special character");
    }
    if (!user.getSurName().matches(onlyCharRegex)) {
     errors.put(UserConstants.SUR_NAME, "Surname name cannot contain numeric value or special character");
    }
    if(user.getPassword().length()<6){
      errors.put(UserConstants.PASSWORD, "Password most contain atleast 6 characters");
    }
    if(user.getAge()>80){
      errors.put(UserConstants.AGE, "Age cannot be greater than 80");
    }

  }

 

}
