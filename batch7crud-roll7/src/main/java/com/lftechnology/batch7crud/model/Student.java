package com.lftechnology.batch7crud.model;

/**
 * Created by leapfrog on 1/18/16.
 */
public class Student {
    private int id;
    private int roll;
    private String name;
    private String address;

    public Student() {
    }

    public Student( int id,String name, String address, int roll) {
        this.name = name;
        this.address = address;
        this.roll = roll;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
