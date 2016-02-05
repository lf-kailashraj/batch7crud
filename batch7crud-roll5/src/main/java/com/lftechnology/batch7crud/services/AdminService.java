package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.model.Employee;

public interface AdminService {
    Employee login(String name, String password);  
}
