package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDao;
import com.lftechnology.batch7crud.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by romit on 1/14/16.
 */
public class EmployeeService {
    public void addEmployee(Employee employee) throws SQLException{
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.insertAllData(employee);
    }
    public List<Employee> getEmployees(int page) throws  SQLException{
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.getPageData(page);
    }
    public void deleteEmployee(int empId) throws SQLException{
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.delete(empId);
    }
}
