package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.util.List;

/**
 * Created by pratishshr on 1/14/16.
 */
public class EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeService() throws DataException {
        employeeDao = new EmployeeDao();
    }

    public List<Employee> fetch() throws DataException {
        return employeeDao.fetch();
    }

    public Employee fetch(int id) throws DataException {
        return employeeDao.fetch(id);
    }

    public void save(Employee employee) throws DataException {
        employeeDao.insert(employee);
    }

    public void update(Employee employee) throws DataException {
        employeeDao.update(employee);
    }

    public void deleteEmployee(int id) throws DataException {
        employeeDao.deleteEmployee(id);
    }

}
