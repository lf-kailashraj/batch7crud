package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/19/16.
 */
public class EmployeeService {
  private EmployeeDao employeeDao;

  public EmployeeService() {
    employeeDao = new EmployeeDao();
  }

  public void insert(Employee employee) throws DataException {
    employeeDao.insert(employee);
  }

  public List<Employee> fetch(int noOfRecordsPerPage, int page) throws DataException {
    return employeeDao.fetch(noOfRecordsPerPage, page);
  }

  public Employee fetchById(int id) throws DataException {
    return employeeDao.fetchById(id);
  }

  public void update(Employee employee) throws DataException {
    employeeDao.update(employee);
  }

  public int getTotalNoOfRecords() throws DataException {
    return employeeDao.getTotalNoOfRecords();
  }

  public void delete(int empId) throws DataException {
    employeeDao.delete(empId);
  }
}
