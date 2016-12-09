package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.constants.AttributeConstant;
import com.lftechnology.batch7crud.constants.MessageConstant;
import com.lftechnology.batch7crud.constants.ParameterConstant;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.ValidationException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by sanjay on 1/28/16.
 */
public class StudentFactory implements Factory<Student> {

  @Override
  public Student createObject(Map<String, String> input) throws ValidationException {
    Student student = new Student();
    Map<String, String> error = new HashMap<>();
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
        try {
          student.setGrade(Integer.parseInt((String) mapEntry.getValue()));
        } catch (NumberFormatException e) {
          error.put(AttributeConstant.ERROR_GRADE, MessageConstant.ERROR_GRADE);
          ValidationException validationException = new ValidationException();
          validationException.setErrors(error);
          throw validationException;
        }
      }
    }
    return student;
  }
}
