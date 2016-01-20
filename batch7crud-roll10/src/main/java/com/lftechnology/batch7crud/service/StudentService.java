package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.dao.StudentDao;
import com.lftechnology.batch7crud.exception.DataException;

import java.util.List;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/14/16
 */
public class StudentService {
  private StudentDao studentDao = new StudentDao();

  public List<Student> fetch(Integer offset, Integer limit) throws DataException {
    return studentDao.fetch(offset, limit);
  }

  public void insert(Student student) throws DataException {
    studentDao.insert(student);
  }

  public void delete(Integer studentId) throws DataException {
    studentDao.delete(studentId);
  }

  public void update(Student student) throws DataException {
    studentDao.update(student);
  }

  public Student fetchById(Integer id) throws DataException {
    return studentDao.fetchById(id);
  }

  public Integer fetchTotalRecordNumber() throws DataException {
    return studentDao.fetchTotalRecordNumber();
  }
}
