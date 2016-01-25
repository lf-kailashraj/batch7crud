package com.lftechnology.batch7crud.validator;

import static com.lftechnology.batch7crud.constant.ParamConstants.*;

import com.lftechnology.batch7crud.model.Employee;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pratishshr on 1/25/16.
 */
public class EmployeeValidator implements Validator<Employee> {

  @Override
  public Employee createObject(Map<String, String> input) {
    String firstName = input.get(PARAM_FIRST_NAME).trim();
    String lastName = input.get(PARAM_LAST_NAME).trim();
    String station = input.get(PARAM_STATION).trim();

    Employee employee = new Employee();
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setStation(station);

    return employee;
  }

  @Override
  public Map<String, String> validate(Employee e) {
    Map<String, String> errors = new HashMap<String, String>();

    String firstName = e.getFirstName().trim();
    String lastName = e.getLastName().trim();
    String station = e.getStation().trim();

    if (isNullOrEmpty(firstName) || !isString(firstName)) {
      errors.put(PARAM_FIRST_NAME, "Check first name");
    }

    if (isNullOrEmpty(lastName) || !isString(lastName)) {
      errors.put(PARAM_LAST_NAME, "Check Last Name");
    }

    if (isNullOrEmpty(station)) {
      errors.put(PARAM_STATION, "Check Station");
    }

    return errors;
  }

  private boolean isNullOrEmpty(String argument) {
    return "".equals(argument);
  }

  private boolean isString(String argument) {
    return argument.matches("[A-Za-z]*");
  }

}
