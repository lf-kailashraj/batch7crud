package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.model.Employee;

import java.util.Map;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/26/16.
 */
public class EmployeeFactory {
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
}
