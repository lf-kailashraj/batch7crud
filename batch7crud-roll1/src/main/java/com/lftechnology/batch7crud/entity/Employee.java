package com.lftechnology.batch7crud.entity;

/**
 * Employee Entity holds basic properties of Employee
 *
 * @Author Kiran Pariyar <kiranpariyar@lftechnology.com>
 * Created on 1/18/16
 */

public class Employee {
  private int id;
  private String userName;
  private String password;
  private String fullName;
  private String department;
  private String address;

  public Employee() {
    //empty constructor
  }

  public Employee(String userName, String password, String fullName, String department, String address) {

    this.userName = userName;
    this.password = password;
    this.fullName = fullName;
    this.department = department;
    this.address = address;

  }

  public int getId() {

    return id;
  }

  public void setId(int id) {

    this.id = id;
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

  public String getFullName() {

    return fullName;
  }

  public void setFullName(String fullName) {

    this.fullName = fullName;
  }

  public String getDepartment() {

    return department;
  }

  public void setDepartment(String department) {

    this.department = department;
  }

  public String getAddress() {

    return address;
  }

  public void setAddress(String address) {

    this.address = address;
  }
}