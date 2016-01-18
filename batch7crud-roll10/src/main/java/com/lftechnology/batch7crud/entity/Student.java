package com.lftechnology.batch7crud.entity;

/**
 * @Author binodnme
 * Created on 1/14/16
 */
public class Student extends Person{
//    private Integer studentId;
    private String department;
    private String batch;
    private Integer roll;


//    public Integer getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(Integer studentId) {
//        this.studentId = studentId;
//    }

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
