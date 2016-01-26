package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.model.Employee;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/25/16.
 */
public class EmployeeValidator implements Validator<Employee> {

  @Override public Employee createObject(Map<String, String> input) {
    String name = input.get(AttributeConstants.NAME).trim();
    String address = input.get(AttributeConstants.ADDRESS).trim();
    String designation = input.get(AttributeConstants.DESIGNATION).trim();
    String phone = input.get(AttributeConstants.PHONE).trim();
    Employee employee = new Employee();
    employee.setName(name);
    employee.setAddress(address);
    employee.setDesignation(designation);
    employee.setPhone(phone);
    return employee;
  }

  @Override public Map<String, String> validate(Employee employee) {
    Map<String, String> errors = new HashMap<>();
    String name = employee.getName().trim();
    String address = employee.getAddress().trim();
    String designation  = employee.getDesignation().trim();
    String phone = employee.getPhone().trim();
    if (isNullOrEmpty(name) || !isString(name)) {
      errors.put(AttributeConstants.NAME, "Check name");
    }

    if (isNullOrEmpty(address)) {
      errors.put(AttributeConstants.ADDRESS, "Check address");
    }

    if (isNullOrEmpty(designation)) {
      errors.put(AttributeConstants.DESIGNATION, "Check designation");
    }

    if (isNullOrEmpty(phone)) {
      errors.put(AttributeConstants.PHONE, "Check phone");
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
