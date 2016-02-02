package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.util.PasswordGenerator;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/27/16.
 */
public class UserService {
  private UserDao adminDao;
  private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

  public UserService() {
    adminDao = new UserDao();
  }

  public boolean isValidUser(String userName, String password) throws DataException {
    String hashedPassword;
    try {
      hashedPassword = PasswordGenerator.getMD5(password);
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
    return adminDao.checkValid(userName, hashedPassword);
  }
}
