package com.lftechnology.batch7crud.entity;


/**
 * Admin class holds the admin credentials for login and status
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/17/16
 */
public class Admin extends Person {
  private Integer userId;
  //other admin specific field will be added here

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
