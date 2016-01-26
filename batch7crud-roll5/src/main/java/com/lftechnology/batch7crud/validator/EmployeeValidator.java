package com.lftechnology.batch7crud.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.ValidationException;

import com.lftechnology.batch7crud.constants.EmployeeAttributeConstants;
import com.lftechnology.batch7crud.exception.ValidationExceptions;
import com.lftechnology.batch7crud.model.Employee;

public class EmployeeValidator implements Validator<Employee> {

    @Override
    public Employee createObject(Map<String, String> input) {
        Employee emp = new Employee();
        emp.setFirstName((String) input.get(EmployeeAttributeConstants.FIRST_NAME));
        emp.setLastName((String) input.get(EmployeeAttributeConstants.LAST_NAME));
        emp.setPassword((String) input.get(EmployeeAttributeConstants.PASSWORD));
        emp.setDepartment((String) input.get(EmployeeAttributeConstants.DEPARTMENT));
        emp.setAddress((String) input.get(EmployeeAttributeConstants.ADDRESS));
        return emp;
    }

    @Override
    public boolean isValid(Employee employee) throws ValidationException, ValidationExceptions {
        Map<String, String> error = new HashMap<String, String>();
        if (employee.getFirstName().isEmpty() || isNotString(employee.getFirstName())) {
            error.put("firstName", "Please Enter Valid First Name");
        }

        if (employee.getLastName().isEmpty() || isNotString(employee.getLastName())) {
            error.put("lastName", "Please Enter Valid Last Name");
        }

        if (employee.getPassword().isEmpty()) {
            error.put("pass", "Please Enter Valid Password");
        }

        if (employee.getDepartment().isEmpty() || isNotString(employee.getDepartment())) {
            error.put("department", "Please Enter Valid Department");
        }

        if (employee.getAddress().isEmpty() || isNotString(employee.getAddress())) {
            error.put("address", "Please Enter Valid Address");
        }

        if (error.isEmpty()) {
            return true;
        } else {
            ValidationExceptions validationExceptions = new ValidationExceptions();
            validationExceptions.setErrorMessage(error);
            throw validationExceptions;
        }

    }

    public boolean isNotString(String myString) {
        if (Pattern.matches("[a-zA-Z]+", myString)) {
            return false;
        }
        return true;
    }

}