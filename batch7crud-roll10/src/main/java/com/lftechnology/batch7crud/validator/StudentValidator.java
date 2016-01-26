package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.utils.DateUtils;
import com.lftechnology.batch7crud.utils.annotation.FieldValidator;

import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/25/16
 */
public class StudentValidator implements Validator<Student> {

  @Override
  public Map<String, String> validate(Student student) {
    Map<String, String> errors = new HashMap<>();

    Date dob = student.getDob();

    if(dob.after(new Date())){
      errors.put("dob", "date greater than current date");
    }

    return errors;
  }
}
