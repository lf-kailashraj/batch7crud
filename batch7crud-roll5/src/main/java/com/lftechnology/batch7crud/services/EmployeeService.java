package com.lftechnology.batch7crud.services;

import java.util.List;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

public interface EmployeeService {
    List<Employee> fetch(int limit, int offSet) throws DataException;
    
    Employee fetchById(int id) throws DataException;
    
    void create(Employee employee) throws DataException;
    
    void edit(Employee employee) throws DataException;
    
    void deleteById(int id) throws DataException;
    
    int count() throws DataException;

}
