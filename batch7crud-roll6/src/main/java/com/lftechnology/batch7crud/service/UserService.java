package com.lftechnology.batch7crud.service;

import java.util.List;
import java.util.Map;

import com.lftechnology.batch7crud.dao.UserDAOImpl;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.model.User;
import com.lftechnology.batch7crud.validator.UserValidator;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 18, 2016
 */
public class UserService {

  UserDAOImpl userDAOImpl = new UserDAOImpl();

  public void addUser(User user, Map<String, String> errors) throws DataException, ValidationException { // NOSONAR

    UserValidator userValidator = new UserValidator();
    userValidator.validate(user,errors);

    if (errors.isEmpty()) {
      userDAOImpl.add(user);

    } else {
      throw new ValidationException(errors);
    }
  }

  public void deleteUser(int userID) throws DataException {
    userDAOImpl.delete(userID);
  }

  public void update(User user,Map<String, String> errors) throws DataException, ValidationException {//NOSONAR
    UserValidator userValidator = new UserValidator();
    userValidator.validate(user,errors);

    if (errors.isEmpty()) {
      userDAOImpl.update(user);

    } else {
      throw new ValidationException(errors);
    }
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

  public void checkUser(String username, String password) {
    
  }

}
