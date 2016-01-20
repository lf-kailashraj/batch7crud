package com.lftechnology.batch7crud.service;

import java.util.List;

import com.lftechnology.batch7crud.dao.UserDAOImpl;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

/**
 * @author madandhungana <madandhungana@lftechnology.com> Jan 18, 2016
 */
public class UserService {

    UserDAOImpl userDAOImpl = new UserDAOImpl();

    public void addUser(User user) throws DataException {

        userDAOImpl.add(user);
    }

    public void deleteUser(int userID) throws DataException {
        userDAOImpl.delete(userID);
    }

    public void update(User user) throws DataException {
        userDAOImpl.update(user);
    }

    public List<User> fetch(int page,int limit) throws DataException {

        return userDAOImpl.fetch(page,limit);
    }

    public User fetchByID(int userID) throws DataException {

        return userDAOImpl.fetchByID(userID);
    }

    public int totalUser() {
        return userDAOImpl.totalUser();
    }

}
