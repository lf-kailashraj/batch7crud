package com.lftechnology.batch7crud.util;

import java.util.Map;

import com.lftechnology.batch7crud.constants.EmployeeAttributeConstants;
import com.lftechnology.batch7crud.model.Employee;

public class EmployeeFactory {
    public Employee createObjects(Map<String, String> input) {
        Employee emp = new Employee();
        emp.setFirstName((String) input.get(EmployeeAttributeConstants.FIRST_NAME));
        emp.setLastName((String) input.get(EmployeeAttributeConstants.LAST_NAME));
        emp.setPassword((String) input.get(EmployeeAttributeConstants.PASSWORD));
        emp.setDepartment((String) input.get(EmployeeAttributeConstants.DEPARTMENT));
        emp.setAddress((String) input.get(EmployeeAttributeConstants.ADDRESS));
        return emp;
    }

}
