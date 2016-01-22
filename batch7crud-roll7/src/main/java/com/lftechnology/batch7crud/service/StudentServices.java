package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.StudentDataAccess;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Student;

import java.util.List;

/**
 * Created by leapfrog on 1/18/16.
 */
public class StudentServices {
  private StudentDataAccess studentDao = new StudentDataAccess();

  public void addNew(Student student) throws DataException {
    studentDao.addNew(student);
  }

  public List<Student> fetch(int limit, int offset) throws DataException {
    return studentDao.fetch(limit,offset);
  }

  public int fetchTotal() throws DataException {
    return studentDao.fetchTotal();
  }

  public Student fetchById(int id) throws DataException {
    return studentDao.fetchById(id);
  }

  public void update(Student student) throws DataException {
    studentDao.update(student);
  }

  public void delete(int id) throws DataException {
    studentDao.delete(id);
  }
}
