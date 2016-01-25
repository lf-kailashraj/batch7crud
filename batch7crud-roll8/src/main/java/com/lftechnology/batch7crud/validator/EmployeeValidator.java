package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.model.Employee;

import java.util.HashMap;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/25/16.
 */
public class EmployeeValidator {
  HashMap<String, String> employeeErrors = new HashMap<>();

  public HashMap<String, String> validate(Employee employee) {
    validateName(employee.getName());
//    validateAddress(employee.getAddress());
//    validateDesignation(employee.getDesignation());
//    validatePhone(employee.getPhone());
    return employeeErrors;
  }

  private void validateName(String name) {

    if (name == null) {
      employeeErrors.put("name", "Name can't be null.");
    }
    else if (name.equals("") || name.trim().isEmpty()) {
      employeeErrors.put("name", "Name can't be empty");
    }
    else if (hasNumbers(name)) {
      employeeErrors.put("name", "Name can't have any numbers in it.");
    }
    else {
      employeeErrors.remove("name");
    }
  }

//  private String  validateAddress(String address) {
//
//  }
//
//  private String validateDesignation(String designation) {
//
//  }
//
//  private String validatePhone(String phone) {
//
//  }

  private boolean hasNumbers(String val) {
    String pattern = ".*\\d+.*";
    if (val.matches(pattern)) {
      return true;
    } else {
      return false;
    }
  }
}
