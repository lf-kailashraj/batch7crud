package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Employee;
import com.lftechnology.batch7crud.exception.DataException;

import java.util.List;

/**
 * EmployeeDAO interface
 * <p>
 * Created by kiran on 1/29/16.
 */
public interface EmployeeDAO {

  void insert(Employee employee) throws DataException;

  List<Employee> fetch(int offset, int limit) throws DataException;

  Employee fetchById(int id) throws DataException;

  void update(Employee employee) throws DataException;

  void delete(int id) throws DataException;

  Integer fetchNoOfRecords() throws DataException;

}
