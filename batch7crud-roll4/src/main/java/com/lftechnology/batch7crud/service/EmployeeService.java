package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pratishshr on 1/14/16.
 */
public class EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeService() throws SQLException, ClassNotFoundException {
        employeeDAO = new EmployeeDAO();
    }

    public List<Employee> getAll() throws SQLException {
        return employeeDAO.getAll();
    }

    public void save(Employee employee) throws SQLException {
        employeeDAO.save(employee);
    }

}
