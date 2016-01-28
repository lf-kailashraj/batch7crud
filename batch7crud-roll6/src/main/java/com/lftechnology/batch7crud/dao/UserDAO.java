package com.lftechnology.batch7crud.dao;

import java.util.List;

import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 18, 2016
 */
public interface UserDAO {

  public void add(User user) throws DataException;

  public void delete(int userID) throws DataException;

  public List<User> fetch(int page, int limit) throws DataException;

  public User fetchByID(int userID) throws DataException;

  public void update(User user) throws DataException;

  public int totalUser();

}
