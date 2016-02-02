package com.lftechnology.batch7crud.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.ValidationException;

import com.lftechnology.batch7crud.exception.ValidationExceptions;
import com.lftechnology.batch7crud.model.Employee;

public class EmployeeValidator implements Validator<Employee> {
    @Override
    public boolean isValid(Employee employee) throws ValidationException, ValidationExceptions {
        Map<String, String> error = new HashMap<String, String>();
        String firstName = employee.getFirstName().trim();
        String lastName = employee.getLastName().trim();
        String password = employee.getPassword();
        String department = employee.getDepartment().trim();
        String address = employee.getAddress();
        if (firstName.isEmpty() || isNotString(firstName)) {
            error.put("firstName", "Please Enter Valid First Name");
        }

        if (lastName.isEmpty() || isNotString(lastName)) {
            error.put("lastName", "Please Enter Valid Last Name");
        }

        if (password.isEmpty()) {
            error.put("pass", "Please Enter Valid Password");
        }

        if (department.isEmpty() || isNotString(department)) {
            error.put("department", "Please Enter Valid Department");
        }

        if (address.isEmpty() || isNotString(address)) {
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

    public static boolean isInteger(String value) {
        String inputValue = "[0-9]*";

        if (value.matches(inputValue)) {
            return true;
        }
        return false;
    }

}