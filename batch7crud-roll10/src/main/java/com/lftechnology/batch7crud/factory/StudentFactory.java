package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.utils.DateUtils;
import com.lftechnology.batch7crud.utils.annotation.FieldValidator;

import static com.lftechnology.batch7crud.constant.EntityConstant.*;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/26/16
 */
public class StudentFactory {
  private static final Logger LOGGER = Logger.getLogger(StudentFactory.class.getName());

  private StudentFactory(){
  }

  public static Student createStudent(Map<String, String> params, Map<String, String> errors) {

    String name = params.get(NAME);
    String address = params.get(ADDRESS);
    String dob = params.get(DOB);
    String department = params.get(DEPARTMENT);
    String batch = params.get(BATCH);
    String rollText = params.get(ROLL);

    Student student = new Student();
    student.setBatch(batch);
    student.setDepartment(department);
    student.setName(name);
    student.setAddress(address);

    try {
      Date date = DateUtils.parse(dob);
      student.setDob(date);
    } catch (ParseException e) {
      errors.put(DOB, "invalid date format");
    }

    try {
      Integer roll = Integer.parseInt(rollText);
      student.setRoll(roll);
    } catch (NumberFormatException e) {
      errors.put(ROLL, "invalid roll number");
    }

    try {
      FieldValidator.validate(student);
    } catch (ValidationException e) {
      errors.putAll(e.getErrors());
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return student;
  }
}
