package com.lftechnology.batch7crud.services;

import java.util.List;

import com.lftechnology.batch7crud.dao.EmployeeDAO;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

public class EmployeeService {
	public List<Employee> fetch() throws DataException{
		EmployeeDAO employeeDAO = new EmployeeDAO();
		return employeeDAO.fetch();
	}
	
	
}
