package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.constants.AppConstant;
import com.lftechnology.batch7crud.constants.AttributeConstant;
import com.lftechnology.batch7crud.constants.MessageConstant;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.factory.StudentFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sanjay on 1/22/16.
 */
public class StudentValidator {
  public Student createObject(Map<String, String> input) throws ValidationException {
    StudentFactory studentFactory = new StudentFactory();
    return studentFactory.createObject(input);
  }

  public void validate(Student student) throws ValidationException {
    Map<String, String> error = new HashMap<>();
    if (!isValid(student.getFirstName())) {
      error.put(AttributeConstant.ERROR_FNAME, MessageConstant.ERROR_FNAME);
    }
    if (!isValid(student.getMiddleName())) {
      error.put(AttributeConstant.ERROR_MNAME, MessageConstant.ERROR_MNAME);
    }
    if (!isValid(student.getLastName())) {
      error.put(AttributeConstant.ERROR_LNAME, MessageConstant.ERROR_LNAME);
    }
    if (!isValid(student.getAddress())) {
      error.put(AttributeConstant.ERROR_ADDRESS, MessageConstant.ERROR_ADDRESS);
    }
    if (!isValid(student.getGrade())) {
      error.put(AttributeConstant.ERROR_GRADE, MessageConstant.ERROR_GRADE);
    }
    if (!error.isEmpty()) {
      ValidationException validationException = new ValidationException();
      validationException.setErrors(error);
      throw validationException;
    }
  }

  public boolean isValid(String input) {
    String pattern = "^[A-z]+$";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(input);
    return m.find() ? true : false;
  }

  public boolean isValid(int input) {
    return (input > AppConstant.GRADE_LIMIT) ? false : true;
  }

}
