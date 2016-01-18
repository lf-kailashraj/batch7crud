package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDAOImpl;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.util.List;

public class EmployeeServiceImpl{

	private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	
	public int create(Employee employee) throws DataException {
		return employeeDAO.insert(employee);
	}

    public List<Employee> fetch() throws DataException{
        return employeeDAO.fetch(1);
    }

    public Employee getEmployeeById(int id) throws DataException{
        return employeeDAO.getEmployeeById(id);
    }

    public int update(Employee employee) throws DataException{
        return employeeDAO.update(employee);
    }

    public int delete(int id) throws DataException{
        return employeeDAO.delete(id);
    }
}