package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.util.List;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/19/16.
 */

public class EmployeeService {
  private EmployeeDao employeeDao;

  public EmployeeService() {
    employeeDao = new EmployeeDao();
  }

  public Employee create(Employee employee) throws DataException {
    return employeeDao.create(employee);
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
