package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

/**
 * Created by achyut on 2/2/16.
 */
public interface UserDao {
  void create(User user) throws DataException;

  boolean checkUser(User user) throws DataException;
}
