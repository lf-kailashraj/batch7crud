package com.lftechnology.batch7crud.entity;

/**
 * Admin class holds the admin credentials for login and status
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/17/16
 */
public class Admin extends Person {
  private String username;
  private String password;
  private String isActive;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIsActive() {
    return isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }
}
