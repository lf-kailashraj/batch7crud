package com.lftechnology.batch7crud.entity;

/**
 * Created by sanjay on 1/14/16.
 */
public class Student {
  private int id;
  private String firstName;
  private String middleName;
  private String lastName;
  private String address;
  private int grade;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "'" + id + "', '" + firstName + "', '" + middleName + "', '" + lastName + "', '" + address + "', '" + grade + "'";
  }
}
