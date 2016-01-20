package com.lftechnology.batch7crud.services;

import java.util.List;
import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

public class EmployeeService {
    EmployeeDAO employeeDAO = new EmployeeDAO();

    public List<Employee> fetch(int pageNo) throws DataException {
        return employeeDAO.fetch(pageNo);
    }

    public Employee fetchById(int id) throws DataException {
        return employeeDAO.fetchById(id);
    }

    public void create(Employee employee) throws DataException {
        employeeDAO.create(employee);
    }

    public void edit(Employee employee, int id) throws DataException {
        employeeDAO.edit(employee, id);
    }

    public void deleteById(int id) throws DataException {
        employeeDAO.deleteById(id);
    }

    public int count() throws DataException {
        return employeeDAO.count();
    }

}
