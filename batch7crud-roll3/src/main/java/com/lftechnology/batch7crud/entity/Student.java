package com.lftechnology.batch7crud.entity;

public class Student {
  private int id;
  private int roll;
  private String name;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setRoll(int roll) {
    this.roll = roll;
  }

  public int getRoll() {
    return roll;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
