package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.utils.StringUtils;

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
  public void validate(Employee employee) throws ValidationException {

    Map<String, String> errors = new HashMap<>();

    if (StringUtils.isNullOrEmpty(employee.getUserName())) {
      errors.put(USER_NAME, "username cannot be empty");
    }

    if (StringUtils.isNullOrEmpty(employee.getPassword())) {
      errors.put(PASSWORD, "password cannot be empty");
    }

    if (StringUtils.isNullOrEmpty(employee.getFullName())) {
      errors.put(FULL_NAME, "fullname cannot be empty");
    }

    if (StringUtils.isNullOrEmpty(employee.getDepartment())) {
      errors.put(DEPARTMENT, "department cannot be empty");
    }

    if (StringUtils.isNullOrEmpty(employee.getAddress())) {
      errors.put(ADDRESS, "address cannot be empty");
    }

    if (employee.getAge() < 1 || employee.getAge() > 100) {
      errors.put(AGE, "invalid age");
    }

    if(!errors.isEmpty()){
      throw new ValidationException(errors);
    }
  }

}
