package com.lftechnology.batch7crud.model;

/**
 * Created by Pratish Shrestha <pratishshrestha@lftechnology.com> on 1/14/16.
 */
public class Employee {
  private int id;
  private String firstName;
  private String lastName;
  private String station;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getStation() {
    return station;
  }

  public void setStation(String station) {
    this.station = station;
  }


}
