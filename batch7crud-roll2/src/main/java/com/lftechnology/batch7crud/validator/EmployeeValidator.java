package com.lftechnology.batch7crud.validator;

import com.lftechnology.batch7crud.constants.AppConstants;
import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.model.Employee;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/25/16.
 */
public class EmployeeValidator implements GenericValidator<Employee> {

  @Override
  public Employee createObject(Map<String, String> input) {
    Employee employee = new Employee();
    String name = input.get(AttributeConstants.NAME);
    String address = input.get(AttributeConstants.ADDRESS);
    String email = input.get(AttributeConstants.EMAIL);
    String contact = input.get(AttributeConstants.CONTACT);

    employee.setName(name);
    employee.setAddress(address);
    employee.setEmail(email);
    employee.setContact(contact);

    return employee;
  }

  @Override
  public Map<String, String> validate(Employee entity) {
    Map<String, String> errors = new HashMap<>();
    String email = entity.getEmail().trim();

    if (isNullOrEmpty(entity.getName().trim())) {
      errors.put(AttributeConstants.NAME, "Set Name");
    }
    if (isNullOrEmpty(entity.getAddress().trim())) {
      errors.put(AttributeConstants.ADDRESS, "Set Address");
    }
    if (isNullOrEmpty(email) || !isValidEmail(email)) {
      errors.put(AttributeConstants.EMAIL, "Email not correct");
    }
    if (isNullOrEmpty(entity.getContact().trim())) {
      errors.put(AttributeConstants.CONTACT, "Set Contact");
    }
    return errors;
  }

  private boolean isNullOrEmpty(String value) {
    return value == null || value.isEmpty();
  }

  private boolean isValidEmail(String email) {
    return email.matches(AppConstants.EMAIL_PATTERN);
  }
}
