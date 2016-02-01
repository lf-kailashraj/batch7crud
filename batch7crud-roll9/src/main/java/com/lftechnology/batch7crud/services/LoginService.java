package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.LoginDAO;
import com.lftechnology.batch7crud.entity.User;
import com.lftechnology.batch7crud.exception.DataException;

/**
 * Created by sanjay on 1/29/16.
 */
public class LoginService {
  private LoginDAO loginDAO = new LoginDAO();

  public boolean authenticate(User user) throws DataException {
    return loginDAO.authenticate(user);
  }
}
