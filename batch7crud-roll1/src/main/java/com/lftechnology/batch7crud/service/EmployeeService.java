package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.util.List;

public class EmployeeService {

  private EmployeeDAO employeeDAO = new EmployeeDAO();

  public void create(Employee employee) throws DataException {
    employeeDAO.insert(employee);
  }

  public List<Employee> fetch(int offset, int limit) throws DataException {
    return employeeDAO.fetch(offset, limit);
  }

  public Employee fetchById(int id) throws DataException {
    return employeeDAO.fetchById(id);
  }

  public Integer fetchNoOfRecords() throws DataException {
    return employeeDAO.fetchNoOfRecords();
  }

  public void update(Employee employee, int id) throws DataException {
    employeeDAO.update(employee, id);
  }

  public void delete(int id) throws DataException {
    employeeDAO.delete(id);
  }
}
