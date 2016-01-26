package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.entity.Employee;

import java.util.HashMap;
import java.util.Map;

import static com.lftechnology.batch7crud.constant.EntityConstant.*;
import static com.lftechnology.batch7crud.constant.EntityConstant.ADDRESS;
import static com.lftechnology.batch7crud.constant.EntityConstant.AGE;

/**
 * EmployeeValidator validates Employee related user input data
 * It checks if input data are empty or null
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/25/16.
 */

public class EmployeeValidator implements Validator<Employee> {

  @Override
  public Map<String, String> validate(Employee employee) {

    Map<String, String> errors = new HashMap<>();

    if (isNullOrEmpty(employee.getUserName().trim())) {
      errors.put(USER_NAME, "username cannot be empty");
    }

    if (isNullOrEmpty(employee.getPassword().trim())) {
      errors.put(PASSWORD, "password cannot be empty");
    }

    if (isNullOrEmpty(employee.getFullName().trim())) {
      errors.put(FULL_NAME, "fullname cannot be empty");
    }

    if (isNullOrEmpty(employee.getDepartment().trim())) {
      errors.put(DEPARTMENT, "department cannot be empty");
    }

    if (isNullOrEmpty(employee.getAddress().trim())) {
      errors.put(ADDRESS, "address cannot be empty");
    }

    if (employee.getAge() < 1 || employee.getAge() > 100) {
      errors.put(AGE, "invalid age");
    }

    return errors;
  }

  public boolean isNullOrEmpty(String value) {
    return value == null || value.isEmpty();
  }
}
