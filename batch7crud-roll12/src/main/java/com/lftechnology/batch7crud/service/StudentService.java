package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.model.Student;

import java.util.List;

/**
 * Created by sagarmatha on 2/4/16.
 */
public interface StudentService {

  Student insert(Student student);

  List<Student> viewList(int page, int limit);

  Student viewDetail(int studentID);

  Student update(Student student);

  void delete(int id);

  int countStudents();

  boolean findRecord(int studentID);

}
