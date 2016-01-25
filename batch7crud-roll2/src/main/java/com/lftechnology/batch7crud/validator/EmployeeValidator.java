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
    String name = entity.getName().trim();
    String address = entity.getAddress().trim();
    String email = entity.getEmail().trim();
    String contact = entity.getContact().trim();

    if (isEmpty(name)) {
      errors.put(AttributeConstants.NAME, "Set Name");
    }
    if (isEmpty(address)) {
      errors.put(AttributeConstants.ADDRESS, "Set Address");
    }
    if (isEmpty(email) || !isValidEmail(email)) {
      errors.put(AttributeConstants.EMAIL, "Email not correct");
    }
    if (isEmpty(contact)) {
      errors.put(AttributeConstants.CONTACT, "Set Contact");
    }
    return errors;
  }

  private boolean isEmpty(String employeeInfo) {
    return "".equals(employeeInfo) || " ".equals(employeeInfo);
  }

  private boolean isValidEmail(String email) {
    return email.matches(AppConstants.EMAIL_PATTERN);
  }
}
