package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.model.Employee;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/25/16.
 */
public class EmployeeValidator {
  public Map<String, String> validate(Employee employee) {
    Map<String, String> errors = new HashMap<>();
    String userName = employee.getName().trim();
    String address = employee.getAddress().trim();
    String email = employee.getEmail().trim();
    String contact = employee.getContact().trim();

    if ("".equals(userName) || " ".equals(userName)) {
      errors.put("name", "Set Name");
    }
    if ("".equals(address) || " ".equals(address)) {
      errors.put("address", "Set Address");
    }
    if ("".equals(email) || " ".equals(email)) {
      errors.put("email", "Set Email");
    }
    if ("".equals(contact) || " ".equals(email)) {
      errors.put("contact", "Set Contact");
    }
    return errors;
  }
}
