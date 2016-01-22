package com.lftechnology.batch7crud.dao;

import java.util.List;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Employee;

public interface EmployeeDAO {
    List<Employee> fetch(int limit, int offSet) throws DataException;
    
    Employee fetchById(int id) throws DataException;
    
    void deleteById(int id) throws DataException;
    
    void create(Employee employee) throws DataException;
    
    void edit(Employee employee) throws DataException;
    
    int count() throws DataException;

}
