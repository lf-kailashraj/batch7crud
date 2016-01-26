package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import java.util.List;
import java.util.Map;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/19/16.
 */

public class EmployeeService {
  private EmployeeDao employeeDao;
  private EmployeeValidator validator;

  public EmployeeService() {
    employeeDao = new EmployeeDao();
    validator = new EmployeeValidator();
  }

  public Employee create(Employee employee) throws DataException, ValidationException {
    Map<String, String> errors= validator.validate(employee);
    if (errors.isEmpty()) {
      return employeeDao.create(employee);
    }
    else {
      throw new ValidationException(errors);
    }
  }

  public List<Employee> fetch(Integer pageLimit, Integer offset) throws DataException {
    return employeeDao.fetch(pageLimit, offset);
  }

  public Employee fetchById(Integer id) throws DataException {
    return employeeDao.fetchById(id);
  }

  public Employee edit(Employee employee) throws DataException {
    return employeeDao.edit(employee);
  }

  public void delete(Integer id) throws DataException {
    employeeDao.delete(id);
  }

  public Integer count() throws DataException {
    return employeeDao.count();
  }

}
