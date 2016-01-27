package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.constant.EntityConstant;
import com.lftechnology.batch7crud.entity.Student;

import java.util.*;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/25/16
 */
public class StudentValidator implements Validator<Student> {

  @Override
  public Map<String, String> validate(Student student) {
    Map<String, String> errors = new HashMap<>();

    Date dob = student.getDob();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.YEAR, 10);
    Date date = calendar.getTime();

    if (dob.after(date)) {
      errors.put(EntityConstant.DOB, "date greater than current date");
    }

    if(student.getRoll() < 1){
      errors.put(EntityConstant.ROLL, "roll number should be positive integer");
    }

    return errors;
  }
}
