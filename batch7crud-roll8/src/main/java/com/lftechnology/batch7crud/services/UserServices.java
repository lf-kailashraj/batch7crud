package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishma on 1/18/16.
 */
public class UserServices {
  List<User> userList = new ArrayList<User>();
  public Boolean create(User user) throws DataException {
    UserDao userDao = new UserDao();
    userDao.create(user);
    return null;
  }

}
