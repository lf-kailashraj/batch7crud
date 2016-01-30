package com.lftechnology.batch7crud.service;

import java.util.List;

import com.lftechnology.batch7crud.dao.UserDAOImpl;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.validator.PasswordValidator;
import com.lftechnology.batch7crud.validator.UserValidator;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 18, 2016
 */
public class UserService {

  UserDAOImpl userDAOImpl = new UserDAOImpl();

  public void addUser(User user) throws DataException, ValidationException { // NOSONAR
    UserValidator userValidator = new UserValidator();
    PasswordValidator passwordValidator = new PasswordValidator();
    userValidator.validate(user);
    passwordValidator.validateBusinessLogic(user.getPassword());
    userDAOImpl.add(user);
  }

  public void deleteUser(int userID) throws DataException {
    userDAOImpl.delete(userID);
  }

  public void update(User user) throws DataException, ValidationException {// NOSONAR
    UserValidator userValidator = new UserValidator();
    userValidator.validate(user);
    userDAOImpl.update(user);

  }

  public List<User> fetch(int offset, int limit) throws DataException {

    return userDAOImpl.fetch(offset, limit);
  }

  public User fetchByID(int userID) throws DataException {

    return userDAOImpl.fetchByID(userID);
  }

  public int totalUser() {
    return userDAOImpl.totalUser();
  }

  public User fetchUser(String username, String password) throws DataException {
    return userDAOImpl.fetchUser(username, password);
  }

}
