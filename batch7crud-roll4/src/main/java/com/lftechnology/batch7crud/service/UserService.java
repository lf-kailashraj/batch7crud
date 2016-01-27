package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

/**
 * Created by pratishshr on 1/27/16.
 */

public class UserService {

  public boolean isValidUser(String username, String password) throws DataException {
    UserDao userDao = new UserDao();
    return userDao.isAvailable(username, password);
  }
}
