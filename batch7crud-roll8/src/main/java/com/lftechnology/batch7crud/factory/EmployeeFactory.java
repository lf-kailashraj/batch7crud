package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.constants.AttributeConstants;
import com.lftechnology.batch7crud.model.Employee;

import java.util.Map;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/26/16.
 */

public class EmployeeFactory implements Factory<Employee> {

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
}
