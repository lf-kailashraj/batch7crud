package com.lftechnology.batch7crud.validator;

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
    if ("".equals(entity.getName()) || " ".equals(entity.getName())) {
      errors.put(AttributeConstants.NAME, "Set Name");
    }
    if ("".equals(entity.getAddress()) || " ".equals(entity.getAddress())) {
      errors.put(AttributeConstants.ADDRESS, "Set Address");
    }
    if ("".equals(entity.getEmail()) || " ".equals(entity.getEmail())) {
      errors.put(AttributeConstants.EMAIL, "Set Email");
    }
    if ("".equals(entity.getContact()) || " ".equals(entity.getContact())) {
      errors.put(AttributeConstants.CONTACT, "Set Contact");
    }
    return errors;
  }
}
