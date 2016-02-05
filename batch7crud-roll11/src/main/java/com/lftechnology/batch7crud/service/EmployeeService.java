package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import java.util.List;

/**
 * Created by achyut on 1/26/16.
 */
public class EmployeeService {

  private EmployeeDao employeeDao = new EmployeeDao();

  EmployeeValidator validator = new EmployeeValidator();

  public List<Employee> getAllStudents() throws DataException {
    return employeeDao.getAllEmployees();
  }

  public void create(Employee employee) throws DataException, ValidationException { //NOSONAR
    validator.validateBusinessLogic(employee);
    employeeDao.create(employee);
  }

  public Employee getEmployeeById(int id) throws DataException {
    return employeeDao.getEmployeeById(id);
  }

  public void update(Employee employee) throws DataException, ValidationException { //NOSONAR
      validator.validateBusinessLogic(employee);
      employeeDao.update(employee);
  }

  public void delete(int id) throws DataException {
    employeeDao.delete(id);
  }
}
