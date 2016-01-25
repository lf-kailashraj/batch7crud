package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.dao.StudentDao;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.validator.StudentValidator;

import java.util.List;
import java.util.Map;

/**
 * StudentService class provides CRUD service to the Controller class.
 * It uses StudentDao object to perform the CRUD operation.
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/14/16
 */
public class StudentService {
  private StudentDao studentDao = new StudentDao();

  public List<Student> fetch(Integer offset, Integer limit) throws DataException {
    return studentDao.fetch(offset, limit);
  }

  public void insert(Student student) throws DataException, ValidationException {
    StudentValidator validator = new StudentValidator();
    Map<String, String> errors = validator.validate(student);

    if(errors.isEmpty()){
      studentDao.insert(student);
    }else {
      throw new ValidationException("validation failed", errors);
    }
  }

  public void delete(Integer studentId) throws DataException {
    studentDao.delete(studentId);
  }

  public void update(Student student) throws DataException, ValidationException {
    StudentValidator validator = new StudentValidator();
    Map<String, String> errors = validator.validate(student);

    if(errors.isEmpty()){
      studentDao.update(student);
    }else {
      throw new ValidationException("validation failed", errors);
    }
  }

  public Student fetchById(Integer id) throws DataException {
    return studentDao.fetchById(id);
  }

  public Integer fetchTotalRecordNumber() throws DataException {
    return studentDao.fetchTotalRecordNumber();
  }
}
