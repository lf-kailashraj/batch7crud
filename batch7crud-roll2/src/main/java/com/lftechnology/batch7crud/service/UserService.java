package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.UserDao;
import com.lftechnology.batch7crud.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by romit on 1/14/16.
 */
public class UserService {
    HttpServletRequest request;
    public User getValidUser(User user) throws Exception{
        User validUser=null;
        if(user!=null) {
            UserDao userDao = new UserDao();
            validUser = userDao.getUser(user);
        }
        return validUser;
    }
}
