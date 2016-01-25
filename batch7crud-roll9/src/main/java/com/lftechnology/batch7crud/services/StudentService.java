package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.StudentDAO;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.util.StudentValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sanjay on 1/14/16.
 */

public class StudentService {
  private StudentDAO stdDAO = new StudentDAO();

  public HashMap save(Student student) throws DataException {
    StudentValidator studentValidator = new StudentValidator();
    HashMap error = studentValidator.validate(student);
    if(error.size()<=0){
      stdDAO.insert(student);
    }
    return error;
  }

  public List<Student> fetch(int page, int limit) throws DataException {
    return stdDAO.fetch(page, limit);
  }

  public void delete(int id) throws DataException {
    stdDAO.delete(id);
  }

  public Student fetchById(int id) throws DataException {
    return stdDAO.fetchById(id);
  }

  public HashMap edit(Student student) throws DataException {
    StudentValidator studentValidator = new StudentValidator();
    HashMap error = studentValidator.validate(student);
    if(error.size()<0){
      stdDAO.edit(student);
    }
    return error;
  }

  public int studentCount() throws DataException {
    return stdDAO.studentCount();
  }
}
