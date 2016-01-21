package com.lftechnology.batch7crud.entity;

import java.util.Date;

/**
 * Person class holds the basic details of a person.
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/17/16
 */
public abstract class Person {
  private Integer id;
  private String name;
  private String address;
  private Date dob;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }
}
