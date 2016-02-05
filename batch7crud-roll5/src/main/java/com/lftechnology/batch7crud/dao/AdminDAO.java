package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Admin;

public interface AdminDAO {
    Admin login(String name, String password) throws DataException;
}
