package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.util.List;

/**
 * Created by grishma on 1/19/16.
 */
public class EmployeeServices {

  public void create(Employee employee) throws DataException {
    EmployeeDao employeeDao = new EmployeeDao();
    employeeDao.create(employee);
  }

  public List<Employee> fetch(Integer pageLimit, Integer pageNo) throws DataException {
    EmployeeDao employeeDao = new EmployeeDao();
    return employeeDao.fetch(pageLimit, pageNo);
  }

  public Employee fetchById(Integer id) throws DataException {
    EmployeeDao employeeDao = new EmployeeDao();
    return employeeDao.fetchById(id);
  }

  public void edit(Employee employee, Integer id) throws DataException {
    EmployeeDao employeeDao = new EmployeeDao();
    employeeDao.edit(employee, id);
  }

  public void delete(Integer id) throws DataException {
    EmployeeDao employeeDao = new EmployeeDao();
    employeeDao.delete(id);
  }

  public Integer count() throws DataException {
    EmployeeDao employeeDao = new EmployeeDao();
    return employeeDao.count();
  }

}
