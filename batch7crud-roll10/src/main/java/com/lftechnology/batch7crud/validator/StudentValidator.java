package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.constant.EntityConstant;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.ValidationException;

import java.util.*;

/**
 * This class validates the Student object w.r.t the business logic
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/25/16
 */
public class StudentValidator implements Validator<Student> {

  private static final int AGE_RESTRICTION = 10;

  @Override
  public void validate(Student student) throws ValidationException {
    Map<String, String> errors = new HashMap<>();

    Date dob = student.getDob();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.YEAR, -AGE_RESTRICTION);
    Date date = calendar.getTime();

    if (dob.after(date)) {
      errors.put(EntityConstant.DOB, " should be older than "+AGE_RESTRICTION+" years");
    }

    if (student.getRoll() < 1) {
      errors.put(EntityConstant.ROLL, "roll number should be positive integer");
    }

    if (!errors.isEmpty()) {
      throw new ValidationException("validation failed", errors);
    }
  }
}
