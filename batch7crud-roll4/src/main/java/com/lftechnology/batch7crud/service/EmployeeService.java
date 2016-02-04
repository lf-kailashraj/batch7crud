package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import java.util.List;

/**
 * Created by Pratish Shrestha <pratishshrestha@lftechnology.com> on 1/14/16.
 */
public class EmployeeService {

  private EmployeeDao employeeDao;
  private EmployeeValidator validator;

  public EmployeeService() {
    employeeDao = new EmployeeDao();
    validator = new EmployeeValidator();
  }

  public List<Employee> fetch(int page, int recordLimit) throws DataException {
    return employeeDao.fetch(page, recordLimit);
  }

  public Employee fetchById(int id) throws DataException {
    return employeeDao.fetchById(id);
  }

  public void save(Employee employee) throws DataException, ValidationException {  // NOSONAR
    validator.validate(employee);
    employeeDao.insert(employee);
  }

  public void update(Employee employee) throws ValidationException, DataException { //NOSONAR
    validator.validate(employee);
    employeeDao.update(employee);
  }

  public void deleteEmployee(int id) throws DataException {
    employeeDao.deleteEmployee(id);
  }

  public int fetchNoOfRecords() throws DataException {
    return employeeDao.fetchNoOfRecords();
  }
}
