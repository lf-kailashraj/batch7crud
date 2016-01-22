package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */
public class UserService {
  UserDao userDao = new UserDao();

  public void create(User user) throws DataException {
    userDao.create(user);
  }

}
