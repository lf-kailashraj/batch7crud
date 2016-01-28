package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.AuthenticationDao;
import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;

/**
 * @Author binodnme
 * Created on 1/27/16
 */
public class AuthenticationService {
  public User isValid(String username, String password) throws DataException {
    AuthenticationDao authDao = new AuthenticationDao();
    return authDao.isValid(username, password);
  }
}
