package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by romit on 1/14/16.
 */
public class EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeService() throws DataException {
        employeeDao = new EmployeeDao();
    }

    public void insert(Employee employee) throws DataException {
        employeeDao.insert(employee);
    }

    public List<Employee> fetch() throws DataException {
        return employeeDao.fetch();
    }

    public Employee fetch(int id) throws DataException {
        return employeeDao.fetch(id);
    }

    public void update(Employee employee) throws  DataException{
        employeeDao.update(employee);
    }
    public void delete(int empId) throws DataException {
        employeeDao.delete(empId);
    }
}
