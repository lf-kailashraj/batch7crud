package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.util.List;

/**
 * Created by grishma on 1/19/16.
 */
public class EmployeeServices {
//    List<Employee> employeeList = new ArrayList<Employee>();

    public Boolean create(Employee employee) throws DataException {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.create(employee);
        return null;
    }

    public List<Employee> fetch() throws DataException {
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.fetch();
    }

    public Employee fetchById(Integer id) throws DataException {
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.fetchById(id);
    }

    public void edit(Employee employee, Integer id) throws DataException {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.edit(employee, id);
    }

}
