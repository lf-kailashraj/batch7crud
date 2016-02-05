package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.dao.UserDaoImpl;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

/**
 * Created by achyut on 2/2/16.
 */
public class UserService {
  private UserDao userDao = new UserDaoImpl();

  public void create(User user) throws DataException{
    userDao.create(user);
  }

  public boolean checkUser(User user) throws DataException{
    return userDao.checkUser(user);
  }
}
