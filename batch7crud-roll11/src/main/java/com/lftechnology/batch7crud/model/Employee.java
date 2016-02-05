package com.lftechnology.batch7crud.model;

import com.lftechnology.batch7crud.constant.EmployeeConstants;

import java.util.Map;

/**
 * Created by achyut on 1/26/16.
 */
public class Employee {
  private int empId;
  private String name;
  private String address;
  private String department;
  private String dateOfBirth;
  private int salary;
  private String position;


  public Employee(){
    //no argument constructor
  }

  public Employee(Map<String, String> input){
    this.name = input.get(EmployeeConstants.NAME);
    this.address = input.get(EmployeeConstants.ADDRESS);
    this.department = input.get(EmployeeConstants.DEPARTMENT);
    this.position = input.get(EmployeeConstants.POSITION);
    this.salary = Integer.parseInt(input.get(EmployeeConstants.SALARY));
  }



  public int getEmpId() {
    return empId;
  }

  public void setEmpId(int empId) {
    this.empId = empId;
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

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
