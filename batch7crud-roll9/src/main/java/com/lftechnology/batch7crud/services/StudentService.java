package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.StudentDAO;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.util.StudentValidator;
import java.util.List;

/**
 * Created by sanjay on 1/14/16.
 */

public class StudentService {
  private StudentDAO stdDAO = new StudentDAO();

  public Student save(Student student) throws DataException, ValidationException {
    StudentValidator studentValidator = new StudentValidator();
    Student insertStudent;
    try {
      studentValidator.validate(student);
      insertStudent = stdDAO.insert(student);
    } catch (ValidationException e) {
      throw e;
    }
    return insertStudent;
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

  public Student edit(Student student) throws DataException, ValidationException {
    StudentValidator studentValidator = new StudentValidator();
    Student editStudent;
    try {
      studentValidator.validate(student);
      editStudent = stdDAO.edit(student);
    } catch (ValidationException e) {
      throw e;
    }
    return editStudent;
  }

  public int studentCount() throws DataException {
    return stdDAO.studentCount();
  }
}
