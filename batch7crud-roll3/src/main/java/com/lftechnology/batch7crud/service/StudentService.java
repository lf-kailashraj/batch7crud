package com.lftechnology.batch7crud.service;

import java.util.List;

import com.lftechnology.batch7crud.dao.StudentDAO;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.validator.StudentValidator;

public class StudentService {
  private StudentDAO studentDAO = new StudentDAO();

  public void insert(Student student) throws DataException, ValidationException { // NOSONAR
    StudentValidator studentValidator = new StudentValidator();
    if (studentValidator.isValid(student))
      studentDAO.insert(student);
  }

  public List<Student> fetch(int page, int pageSize) throws DataException {
    return studentDAO.fetch(page, pageSize);
  }

  public Student fetchStudentById(int id) throws DataException {
    return studentDAO.fetchStudentById(id);
  }

  public int fetchTotalCount() throws DataException {
    return studentDAO.fetchTotalCount();
  }

  public void edit(Student student) throws DataException, ValidationException { // NOSONAR
    StudentValidator studentValidator = new StudentValidator();
    if (studentValidator.isValid(student))
      studentDAO.edit(student);
  }

  public void delete(int id) throws DataException {
    studentDAO.delete(id);
  }
}
