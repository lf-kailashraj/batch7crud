package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

/**
 * Created by leapfrog on 1/29/16.
 */
public interface UserDaoInterface {
  public User fetch(String name,String password) throws DataException;
}
