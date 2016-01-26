package com.lftechnology.batch7crud.utils;

import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.lftechnology.batch7crud.constant.EntityConstant.*;
import static com.lftechnology.batch7crud.constant.EntityConstant.ADDRESS;
import static com.lftechnology.batch7crud.constant.EntityConstant.AGE;

/**
 * This Class creates Employee object
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/26/16.
 */
public class EmployeeFactory {

  private static final Logger LOGGER = Logger.getLogger(EmployeeService.class.getName());

  public Employee getEmployee(Map<String, String> inputs) throws ValidationException {

    Map<String, String> errors = new HashMap<>();

    try {
      String userName = inputs.get(USER_NAME);
      String password = inputs.get(PASSWORD);
      String fullName = inputs.get(FULL_NAME);
      String department = inputs.get(DEPARTMENT);
      String address = inputs.get(ADDRESS);
      int age = Integer.parseInt(inputs.get(AGE));
      return new Employee(userName, password, fullName, department, address, age);
    } catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errors.put(AGE, "invalid age");
      throw new ValidationException(errors);
    }
  }

}
