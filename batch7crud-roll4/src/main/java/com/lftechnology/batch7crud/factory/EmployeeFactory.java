package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.model.Employee;

import java.util.Map;

import static com.lftechnology.batch7crud.constant.ParamConstants.*;

/**
 * Created by pratishshr on 1/26/16.
 */
public class EmployeeFactory {

  public Employee createObject(Map<String, String> input) {
    String firstName = input.get(PARAM_FIRST_NAME).trim();
    String lastName = input.get(PARAM_LAST_NAME).trim();
    String station = input.get(PARAM_STATION).trim();

    Employee employee = new Employee();
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setStation(station);

    return employee;
  }

}
