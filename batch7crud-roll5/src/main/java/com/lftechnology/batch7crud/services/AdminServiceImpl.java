package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.AdminDAOImpl;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Admin;

public class AdminServiceImpl {
    AdminDAOImpl adminDAOImpl = new AdminDAOImpl();
    

    public Admin login(String name, String password) throws DataException {
        return adminDAOImpl.login(name, password);
    }
    
}
