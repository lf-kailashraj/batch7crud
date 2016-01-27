package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import java.util.List;

/**
 * EmployeeSevice provides Create, Delete, Edit services for employee
 * It invokes EmployeeDAO for CRUD operation
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/18/16
 */

public class EmployeeService {

  private EmployeeDAO employeeDAO = new EmployeeDAO();

  public void create(Employee employee) throws DataException, ValidationException {   //NOSONAR
    EmployeeValidator employeeValidator = new EmployeeValidator();
    employeeValidator.validate(employee);
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

  public void update(Employee employee) throws DataException, ValidationException {   //NOSONAR
    EmployeeValidator employeeValidator = new EmployeeValidator();
    employeeValidator.validate(employee);
    employeeDAO.update(employee);
  }

  public void delete(int id) throws DataException {
    employeeDAO.delete(id);
  }
}
