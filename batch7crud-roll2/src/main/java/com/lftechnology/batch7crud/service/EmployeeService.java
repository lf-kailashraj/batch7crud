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

    public EmployeeService() {
        employeeDao = new EmployeeDao();
    }

    public void insert(Employee employee) throws DataException {
        try {
            employeeDao.insert(employee);
        } catch (DataException de) {
            throw new DataException(de.getMessage());
        }
    }

    public List<Employee> fetch(int page) throws DataException {
        try {
            return employeeDao.fetch(page);
        } catch (DataException de) {
            throw new DataException(de.getMessage());
        }
    }

    public Employee fetchById(int id) throws DataException {
        try {
            return employeeDao.fetchById(id);
        } catch (DataException de) {
            throw new DataException(de.getMessage());
        }
    }

    public void update(Employee employee) throws DataException {
        try {
            employeeDao.update(employee);
        } catch (DataException de) {
            throw new DataException(de.getMessage());
        }
    }

    public void delete(int empId) throws DataException {
        try {
            employeeDao.delete(empId);
        } catch (DataException de) {
            throw new DataException(de.getMessage());
        }
    }
}
