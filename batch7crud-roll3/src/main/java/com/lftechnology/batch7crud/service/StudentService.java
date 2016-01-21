package com.lftechnology.batch7crud.service;

import java.util.List;

import com.lftechnology.batch7crud.dao.StudentDAO;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;

public class StudentService {
  private StudentDAO studentDAO = new StudentDAO();

  public void insert(Student student) throws DataException {
    studentDAO.insert(student);
  }

  public List<Student> fetch(int page, int pageSize) throws DataException {
    return studentDAO.fetch(page, pageSize);
  }

  public Student fetchStudentById(int id) throws DataException {
    return studentDAO.fetchStudentById(id);
  }

  public int fetchTotal() throws DataException {
    return studentDAO.fetchTotal();
  }

  public void edit(Student student, int id) throws DataException {
    studentDAO.edit(student, id);
  }

  public void delete(int id) throws DataException {
    studentDAO.delete(id);
  }
}
