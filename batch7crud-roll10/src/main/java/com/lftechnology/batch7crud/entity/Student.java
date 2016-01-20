package com.lftechnology.batch7crud.entity;

/**
 * Student class holds the student information and getter & setter methods.
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/14/16
 */
public class Student extends Person {
  private String department;
  private String batch;
  private Integer roll;

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getBatch() {
    return batch;
  }

  public void setBatch(String batch) {
    this.batch = batch;
  }

  public Integer getRoll() {
    return roll;
  }

  public void setRoll(Integer roll) {
    this.roll = roll;
  }
}
