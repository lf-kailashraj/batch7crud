package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.Utils.Utilities;
import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

/**
 * Created by leapfrog on 1/27/16.
 */
public class UserService {
  private UserDao userDao = new UserDao();

  public User fetch(String name, String password) throws DataException {
    return userDao.fetch(name, Utilities.getMD5(password));
  }
}
