package com.lftechnology.batch7crud.model;

/**
 * Created by sagarmatha on 1/26/16.
 */
public class Student {
    private int studentID;
    private String firstName;
    private String lastName;
    private int age;
    private String address;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student [studentID=" + studentID + ", firstName=" + firstName
                + ", lastName=" + lastName + ", age=" + age + ", address="
                + address + "]";
    }
}
