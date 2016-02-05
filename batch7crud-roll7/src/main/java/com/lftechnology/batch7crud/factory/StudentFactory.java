package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.exception.ValidatorException;
import com.lftechnology.batch7crud.model.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leapfrog on 1/26/16.
 */
public class StudentFactory {
  public Student createObject(Map input) throws ValidatorException {
    Map<String, String> errors = new HashMap<>();
    String name = (String) input.get("name");
    String address = (String) input.get("address");
    String roll = (String) input.get("roll");

    if (!isInteger(roll) || roll.isEmpty()) {
      errors.put("roll", "Invalid Roll Number");
      throw new ValidatorException("roll", errors);
    }

    Student student = new Student();
    student.setName(name);
    student.setAddress(address);
    student.setRoll(Integer.parseInt(roll));

    return student;

  }

  private boolean isInteger(String string) {
    String onlyInt = "[0-9]*";
    if (string.matches(onlyInt))
      return true;
    return false;
  }
}
