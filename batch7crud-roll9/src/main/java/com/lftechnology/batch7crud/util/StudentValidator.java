package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.constants.AppConstant;
import com.lftechnology.batch7crud.constants.AttributeConstant;
import com.lftechnology.batch7crud.constants.MessageConstant;
import com.lftechnology.batch7crud.constants.ParameterConstant;
import com.lftechnology.batch7crud.entity.Student;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sanjay on 1/22/16.
 */
public class StudentValidator {
  public Student createObject(HashMap error, HashMap input) {
    Student student = new Student();
    Set set = input.entrySet();
    Iterator iterator = set.iterator();
    while (iterator.hasNext()) {
      Map.Entry mapEntry = (Map.Entry) iterator.next();
      if (mapEntry.getKey().equals(ParameterConstant.FIRST_NAME)) {
        student.setFirstName((String) mapEntry.getValue());
      }
      if (mapEntry.getKey().equals(ParameterConstant.MIDDLE_NAME)) {
        student.setMiddleName((String) mapEntry.getValue());
      }
      if (mapEntry.getKey().equals(ParameterConstant.LAST_NAME)) {
        student.setLastName((String) mapEntry.getValue());
      }
      if (mapEntry.getKey().equals(ParameterConstant.ADDRESS)) {
        student.setAddress((String) mapEntry.getValue());
      }
      if (mapEntry.getKey().equals(ParameterConstant.GRADE)) {
        try{
          student.setGrade(Integer.parseInt((String) mapEntry.getValue()));
        } catch(NumberFormatException e){
          error.put(AttributeConstant.ERROR_GRADE,AppConstant.TRUE);
        }
      }
    }
    return student;
  }

  public HashMap validate(Student student) {
    HashMap hm = new HashMap();
    if (!isValid(student.getFirstName())) {
      hm.put(AttributeConstant.ERROR_FNAME, MessageConstant.ERROR_FNAME);
    }
    if (!isValid(student.getMiddleName())) {
      hm.put(AttributeConstant.ERROR_MNAME, MessageConstant.ERROR_MNAME);
    }
    if (!isValid(student.getLastName())) {
      hm.put(AttributeConstant.ERROR_LNAME, MessageConstant.ERROR_LNAME);
    }
    if (!isValid(student.getAddress())) {
      hm.put(AttributeConstant.ERROR_ADDRESS, MessageConstant.ERROR_ADDRESS);
    }
    if (!isValid(student.getGrade())) {
      hm.put(AttributeConstant.ERROR_GRADE, MessageConstant.ERROR_GRADE);
    }
    return hm;
  }

  public boolean isValid(String input) {
    String pattern = "^[A-z]+$";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(input);
    return (m.find()) ? true : false;
  }

  public boolean isValid(int input) {
    return (input > AppConstant.GRADE_LIMIT) ? false : true;
  }

}
