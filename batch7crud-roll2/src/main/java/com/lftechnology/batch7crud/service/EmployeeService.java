package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import java.util.List;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
public class EmployeeService {
  private EmployeeDao employeeDao;

  public EmployeeService() {
    employeeDao = new EmployeeDao();
  }

  public void insert(Employee employee) throws DataException, ValidationException { //NOSONAR
    EmployeeValidator employeeValidator = new EmployeeValidator();
    employeeValidator.validate(employee);
    employeeDao.insert(employee);
  }

  public List<Employee> fetch(int noOfRecordsPerPage, int page) throws DataException {
    return employeeDao.fetch(noOfRecordsPerPage, page);
  }

  public Employee fetchById(int id) throws DataException {
    return employeeDao.fetchById(id);
  }

  public void update(Employee employee) throws DataException, ValidationException { //NOSONAR
    EmployeeValidator employeeValidator = new EmployeeValidator();
    employeeValidator.validate(employee);
    employeeDao.update(employee);
  }

  public int getTotalNoOfRecords() throws DataException {
    return employeeDao.getTotalNoOfRecords();
  }

  public void delete(int empId) throws DataException {
    employeeDao.delete(empId);
  }
}
