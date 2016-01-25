package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.utils.DateUtils;

import java.text.ParseException;
import java.util.*;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/25/16
 */
public class StudentValidator implements Validator<Student> {
  private static final String NAME = "name";
  private static final String ADDRESS = "address";
  private static final String DEPARTMENT = "department";
  private static final String BATCH = "batch";
  private static final String ROLL = "roll";
  private static final String DOB = "dob";

  @Override
  public Student createObject(Map<String, String> params, Map<String, String> errors) {
    String name = params.get(NAME);
    String address = params.get(ADDRESS);
    String dob = params.get(DOB);
    String department = params.get(DEPARTMENT);
    String batch = params.get(BATCH);
    String rollText = params.get(ROLL);

    Student student = new Student();

    try {
      Date date = DateUtils.parse(dob);
      Integer roll = Integer.parseInt(rollText);

      student.setBatch(batch);
      student.setDepartment(department);
      student.setName(name);
      student.setAddress(address);
      student.setDob(date);
      student.setRoll(roll);

    } catch (ParseException e) {
      errors.put(DOB, "invalid date format");

    } catch (NumberFormatException e) {
      errors.put(ROLL, "invalid roll number");

    }

    return student;
  }

  @Override
  public Map<String, String> validate(Student student) {
    Map<String, String> errors = new HashMap<>();

    if (student.getName() == null || student.getName().isEmpty()) {
      errors.put(NAME, "invalid name");
    }

    if (student.getAddress() == null || student.getAddress().isEmpty()) {
      errors.put(ADDRESS, "invalid address");
    }

    if (student.getDepartment() == null || student.getDepartment().isEmpty()) {
      errors.put(DEPARTMENT, "invalid department");

    }

    if (student.getBatch() == null || student.getBatch().isEmpty()) {
      errors.put(BATCH, "invalid batch");

    }

    if (student.getRoll() < 1) {
      errors.put(ROLL, "invalid roll number");
    }

    return errors;
  }
}
