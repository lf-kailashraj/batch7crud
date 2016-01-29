package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.UserDAO;
import com.lftechnology.batch7crud.exception.DataException;

/**
 *
 * Created by kiran on 1/29/16.
 */
public class UserService {

  private UserDAO userDAO = new UserDAO();

  public boolean isValidUser(String userName,String password) throws DataException{
    return userDAO.isValidUser(userName,password);
  }
}
