package com.lftechnology.batch7crud.services;

import java.util.List;

import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

public class EmployeeService {
	EmployeeDAO employeeDAO = new EmployeeDAO();
	public List<Employee> fetch() throws DataException{

		return employeeDAO.fetch();
	}
	
	public boolean create(Employee employee) throws DataException{
		return employeeDAO.create(employee);
	}
	
	
}
