package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.exception.ValidatorException;
import com.lftechnology.batch7crud.model.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com> on 1/25/16.
 */
public class StudentValidator implements Validator<Student> {
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

    public Map<String,String> validate(Student student){
      Map<String,String> errors = new HashMap<>();

      if(student.getName() == null || "".equals(student.getName()))
        errors.put("name","Invalid Name");
      if(student.getAddress() == null || "".equals(student.getAddress()))
        errors.put("address","Invalid Address");
      return errors;
    }

  private boolean isInteger(String string) {
    String onlyInt = "[0-9]*";
    if (string.matches(onlyInt))
      return true;
    return false;
  }

}
