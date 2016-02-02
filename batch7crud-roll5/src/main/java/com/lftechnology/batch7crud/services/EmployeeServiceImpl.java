package com.lftechnology.batch7crud.services;

import java.util.List;

import com.lftechnology.batch7crud.dao.EmployeeDAOImpl;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationExceptions;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

public class EmployeeServiceImpl {
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    EmployeeValidator employeeValidator = new EmployeeValidator();

    public List<Employee> fetch(int limit, int offSet) throws DataException {
        return employeeDAO.fetch(limit, offSet);
    }

    public Employee fetchById(int id) throws DataException {
        return employeeDAO.fetchById(id);
    }

    public void create(Employee employee) throws DataException, ValidationExceptions {
        if (employeeValidator.isValid(employee))
            employeeDAO.create(employee);
    }

    public void edit(Employee employee) throws DataException, ValidationExceptions {
        if (employeeValidator.isValid(employee)) {
            employeeDAO.edit(employee);
        }

    }

    public void deleteById(int id) throws DataException {
        employeeDAO.deleteById(id);
    }

    public int count() throws DataException {
        return employeeDAO.count();
    }
}
