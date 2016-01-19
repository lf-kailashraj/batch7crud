package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

import java.util.List;

public class EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	public void create(Employee employee) throws DataException {
		employeeDAO.insert(employee);
	}

    public List<Employee> fetch() throws DataException{
        return employeeDAO.fetch(1);
    }

    public Employee fetchById(int id) throws DataException{
        return employeeDAO.fetchById(id);
    }

    public void update(Employee employee) throws DataException{
        employeeDAO.update(employee);
    }

    public void delete(int id) throws DataException{
        employeeDAO.delete(id);
    }
}