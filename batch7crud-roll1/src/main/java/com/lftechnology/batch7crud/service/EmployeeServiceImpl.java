package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.factory.DAOFactory;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import java.util.List;

/**
 * EmployeeSevice provides Create, Delete, Edit services for employee
 * It invokes EmployeeDAOImpl for CRUD operation
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/18/16
 */

public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeDAO employeeDAO = DAOFactory.createEmployeeDAO();

  @Override
  public void create(Employee employee) throws DataException, ValidationException {   //NOSONAR
    EmployeeValidator employeeValidator = new EmployeeValidator();
    employeeValidator.validate(employee);
    employeeDAO.insert(employee);
  }

  @Override
  public List<Employee> fetch(int offset, int limit) throws DataException {
    return employeeDAO.fetch(offset, limit);
  }

  @Override
  public Employee fetchById(int id) throws DataException {
    return employeeDAO.fetchById(id);
  }

  @Override
  public Integer fetchNoOfRecords() throws DataException {
    return employeeDAO.fetchNoOfRecords();
  }

  @Override
  public void update(Employee employee) throws DataException, ValidationException {   //NOSONAR
    EmployeeValidator employeeValidator = new EmployeeValidator();
    employeeValidator.validate(employee);
    employeeDAO.update(employee);
  }

  @Override
  public void delete(int id) throws DataException {
    employeeDAO.delete(id);
  }
}