package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.model.Student;

/**
 * Created by sagarmatha on 2/5/16.
 */
public class StudentFactoryImpl implements StudentFactory {
  public Student createStudent(String firstName, String lastName, int age, String address) {
    Student student = new Student();
    student.setFirstName(firstName);
    student.setLastName(lastName);
    student.setAge(age);
    student.setAddress(address);
    return student;
  }

  public Student updateStudent(int id, String firstName, String lastName, int age, String address) {
    Student student = new Student();
    student.setStudentID(id);
    student.setFirstName(firstName);
    student.setLastName(lastName);
    student.setAge(age);
    student.setAddress(address);
    return student;
  }
}
