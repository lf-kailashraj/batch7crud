package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.util.PasswordGenerator;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/18/16.
 */
public class UserService {
  UserDao userDao = new UserDao();
  private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

  public User create(User user) throws DataException {
    return userDao.create(user);
  }

  public User fetchUserByUsernameAndPassword (User user) throws DataException {
    String passwordHash;
    try {
      passwordHash = PasswordGenerator.hashUsingMD5(user.getPassword());
      user.setPassword(passwordHash);
      System.out.println(passwordHash);
      return userDao.fetchUserByUsernameAndPassword(user);
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

}
