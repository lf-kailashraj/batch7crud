package com.lftechnology.batch7crud.model;

import com.lftechnology.batch7crud.constant.UserConstants;

import java.util.Map;

/**
 * Created by achyut on 2/2/16.
 */
public class User {
  private String userName;
  private String password;

  public User(Map<String, String> user) {
    this.userName = user.get(UserConstants.USER_NAME);
    this.password = user.get(UserConstants.PASSWORD);
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
