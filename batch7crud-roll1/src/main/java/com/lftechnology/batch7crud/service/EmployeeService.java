package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;

import java.util.List;

/**
 * EmployeeService Interface
 *
 * Created by kiran on 1/29/16.
 */
public interface EmployeeService {

  void create(Employee employee) throws DataException, ValidationException;  //NOSONAR

  List<Employee> fetch(int offset, int limit) throws DataException;

  Employee fetchById(int id) throws DataException;

  Integer fetchNoOfRecords() throws DataException;

  void update(Employee employee) throws DataException, ValidationException;  //NOSONAR

  void delete(int id) throws DataException ;

}
