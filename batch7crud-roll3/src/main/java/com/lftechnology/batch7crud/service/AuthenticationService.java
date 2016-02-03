package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.AuthenticationDao;
import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;

public class AuthenticationService {

  public boolean isValid(User user) throws DataException {
    AuthenticationDao authDao = new AuthenticationDao();

    return authDao.isValid(user);
  }
}