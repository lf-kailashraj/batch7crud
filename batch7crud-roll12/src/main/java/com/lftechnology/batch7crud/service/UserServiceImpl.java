package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.StudentDao;
import com.lftechnology.batch7crud.dao.StudentDaoImpl;
import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.dao.UserDaoImpl;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.model.User;

/**
 * Created by sagarmatha on 2/2/16.
 */
public class UserServiceImpl implements UserService {
  private UserDao dao = new UserDaoImpl();

  public User getUser() {
    return dao.getUser();
  }
}
