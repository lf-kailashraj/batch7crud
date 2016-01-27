package com.lftechnology.batch7crud.factory;

import static com.lftechnology.batch7crud.constant.StudentConstant.NAME;
import static com.lftechnology.batch7crud.constant.StudentConstant.ROLL;

import java.util.Map;

import com.lftechnology.batch7crud.entity.Student;

public class StudentFactory {
  public Student createObject(Map inputs) {
    Student student = new Student();
    String roll = (String) inputs.get(ROLL);
    String name = (String) inputs.get(NAME);
    student.setName(name);
    if (!roll.isEmpty())
      student.setRoll(Integer.parseInt(roll));
    return student;
  }
}
