package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.exception.ValidationException;
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

  private static final Logger LOGGER = Logger.getLogger(EmployeeFactory.class.getName());

  private EmployeeFactory() {

  }

  public static Employee createEmployee(Map<String, String> formValues) throws ValidationException {

    Map<String, String> errors = new HashMap<>();

    try {
      String userName = formValues.get(USER_NAME);
      String password = formValues.get(PASSWORD);
      String fullName = formValues.get(FULL_NAME);
      String department = formValues.get(DEPARTMENT);
      String address = formValues.get(ADDRESS);
      int age = Integer.parseInt(formValues.get(AGE));
      return new Employee(userName, password, fullName, department, address, age);
    } catch (NumberFormatException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      errors.put(AGE, "invalid age");
      throw new ValidationException(errors);
    }
  }
}
