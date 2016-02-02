package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.lftechnology.batch7crud.constant.ParamConstants.*;

/**
 * Created by pratishshr on 1/25/16.
 */
public class EmployeeValidator implements Validator<Employee> {

  @Override
  public void validate(Employee employee) throws ValidationException {
    Map<String, String> errors = new HashMap<String, String>();

    String firstName = employee.getFirstName();
    String lastName = employee.getLastName();
    String station = employee.getStation();

    if (isNullOrEmpty(firstName) || !isAlphabet(firstName)) {
      errors.put("firstName", "Check First Name");
    }

    if (isNullOrEmpty(lastName) || !isAlphabet(lastName)) {
      errors.put("lastName", "Check Last Name");
    }

    if (isNullOrEmpty(station)) {
      errors.put("station", "Check Station");
    }

    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }

  private boolean isNullOrEmpty(String argument) {
    return argument == null || argument.trim().isEmpty();
  }

  private boolean isAlphabet(String argument) {
    return argument.matches("[A-Za-z]*");
  }

}
