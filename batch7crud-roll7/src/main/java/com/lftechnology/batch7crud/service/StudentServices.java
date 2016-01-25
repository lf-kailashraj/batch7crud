package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.StudentDataAccess;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidatorException;
import com.lftechnology.batch7crud.model.Student;
import com.lftechnology.batch7crud.validator.StudentValidator;

import java.util.List;
import java.util.Map;

/**
 * Created by Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com> on 1/18/16.
 */
public class StudentServices {
  private StudentDataAccess studentDao = new StudentDataAccess();

  public void addNew(Student student) throws DataException, ValidatorException {
    StudentValidator validator = new StudentValidator();
    Map<String, String> errors = validator.validate(student);

    if(errors.isEmpty()){
      studentDao.addNew(student);
    }else {
      throw new ValidatorException("validation failed", errors);
    }
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

  public void update(Student student) throws DataException, ValidatorException {
    StudentValidator validator = new StudentValidator();
    Map<String, String> errors = validator.validate(student);

    if(errors.isEmpty()){
      studentDao.update(student);
    }else {
      throw new ValidatorException("validation failed", errors);
    }
  }

  public void delete(int id) throws DataException {
    studentDao.delete(id);
  }
}
