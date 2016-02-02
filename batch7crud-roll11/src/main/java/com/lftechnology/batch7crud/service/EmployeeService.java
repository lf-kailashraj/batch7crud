package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.Employee;
import com.lftechnology.batch7crud.validator.EmployeeValidator;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by achyut on 1/26/16.
 */
public class EmployeeService {

  private static final Logger LOGGER = Logger.getLogger(EmployeeService.class.getName());
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
