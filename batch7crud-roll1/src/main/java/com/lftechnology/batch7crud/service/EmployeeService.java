package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import java.util.List;
import java.util.Map;

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
    Map<String, String> errors = employeeValidator.validate(employee);
    if (errors.isEmpty()) {
      employeeDAO.insert(employee);
    } else {
      throw new ValidationException(errors);
    }
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
    Map<String, String> errors = employeeValidator.validate(employee);
    if (errors.isEmpty()) {
      employeeDAO.update(employee);
    } else {
      throw new ValidationException(errors);
    }
  }

  public void delete(int id) throws DataException {
    employeeDAO.delete(id);
  }
}
