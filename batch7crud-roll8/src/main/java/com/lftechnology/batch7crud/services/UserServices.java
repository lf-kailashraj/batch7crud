package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.User;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishma on 1/18/16.
 */
public class UserServices {
    List<User> userList = new ArrayList<User>();
    public void create(String name, String username, String email, String password) throws DataException {
        UserDao userDao = new UserDao();
        userDao.create(name, username, email, password);
    }

}
