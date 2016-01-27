package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.constants.ValidatorConstants;
import com.lftechnology.batch7crud.model.Employee;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/25/16.
 */
public class EmployeeValidator implements Validator<Employee> {
  Map<String, String> errors = new HashMap<>();

  @Override public Map<String, String> validate(Employee employee) {
    validateName(employee.getName().trim());
    validateAddress(employee.getAddress().trim());
    validateDesignation(employee.getDesignation().trim());
    validatePhone(employee.getPhone().trim());

    return errors;
  }

  private void validateName(String name) {
    if (name == null) {
      errors.put(ValidatorConstants.EMPLOYEE_NAME, ValidatorConstants.EMPLOYEE_NAME_NULL);
    }
    else if (name.isEmpty()) {
      errors.put(ValidatorConstants.EMPLOYEE_NAME, ValidatorConstants.EMPLOYEE_NAME_EMPTY);
    }
    else if (hasNumbers(name)) {
      errors.put(ValidatorConstants.EMPLOYEE_NAME, ValidatorConstants.EMPLOYEE_NAME_HAS_NUMBERS);
    }
    else {
      errors.remove(ValidatorConstants.EMPLOYEE_NAME);
    }
  }

  private void validateAddress(String address) {
    if (address == null) {
      errors.put(ValidatorConstants.EMPLOYEE_ADDRESS, ValidatorConstants.EMPLOYEE_ADDRESS_NULL);
    }
    else if (address.isEmpty()) {
      errors.put(ValidatorConstants.EMPLOYEE_ADDRESS, ValidatorConstants.EMPLOYEE_ADDRESS_EMPTY);
    }
    else {
      errors.remove(ValidatorConstants.EMPLOYEE_ADDRESS);
    }
  }

  private void validateDesignation(String designation) {
    if (designation == null) {
      errors.put(ValidatorConstants.EMPLOYEE_DESIGNATION, ValidatorConstants.EMPLOYEE_DESIGNATION_NULL);
    }
    else if (designation.isEmpty()) {
      errors.put(ValidatorConstants.EMPLOYEE_DESIGNATION, ValidatorConstants.EMPLOYEE_DESIGNATION_EMPTY);
    }
    else {
      errors.remove(ValidatorConstants.EMPLOYEE_DESIGNATION);
    }
  }

  private void validatePhone(String phone) {
    if (phone == null) {
      errors.put(ValidatorConstants.EMPLOYEE_PHONE, ValidatorConstants.EMPLOYEE_PHONE_NULL);
    }
    else if (phone.isEmpty()) {
      errors.put(ValidatorConstants.EMPLOYEE_PHONE, ValidatorConstants.EMPLOYEE_PHONE_EMPTY);
    }
    else if (hasAlphabets(phone)) {
      errors.put(ValidatorConstants.EMPLOYEE_PHONE, ValidatorConstants.EMPLOYEE_PHONE_HAS_ALPHABETS);
    }
    else {
      errors.remove(ValidatorConstants.EMPLOYEE_PHONE);
    }
  }

  private boolean hasNumbers(String val) {
    String pattern = ".*\\d+.*";
    return val.matches(pattern) ? true : false;
  }

  private boolean hasAlphabets(String val) {
    String pattern = "[a-zA-z]+";
    return val.matches(pattern) ? true : false;
  }
}
