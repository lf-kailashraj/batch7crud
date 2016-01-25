package com.lftechnology.batch7crud.validator;

import java.util.HashMap;
import java.util.Map;

import static com.lftechnology.batch7crud.constant.StudentConstant.*;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.utils.ValidatorUtil;

public class StudentValidator extends ValidatorUtil implements Validator<Student> {

  @Override
  public Student createObject(Map inputs) throws ValidationException {
    Student student = new Student();
    Map<String, String> errors = new HashMap<>();
    String roll = (String) inputs.get(ROLL);
    String name = (String) inputs.get(NAME);
    if (!isInteger(roll))
      errors.put(ROLL, INVALID_ROLL_MESSAGE);
    if (!errors.isEmpty()) {
      ValidationException exception = new ValidationException();
      exception.setErrors(errors);
      throw exception;
    }
    student.setName(name);
    if (!roll.isEmpty())
      student.setRoll(Integer.parseInt(roll));
    return student;

  }

  @Override
  public boolean isValid(Student student) throws ValidationException {
    Map<String, String> errors = new HashMap<>();

    if (!isString(student.getName()) || isEmpty(student.getName()))
      errors.put(NAME, INVALID_NAME_MESSAGE);
    if (student.getRoll() > 200)
      errors.put(ROLL, ROLL_TOO_LARGE_MESSAGE);

    if (!errors.isEmpty()) {
      ValidationException exception = new ValidationException();
      exception.setErrors(errors);
      throw exception;
    }
    return true;
  }
}
